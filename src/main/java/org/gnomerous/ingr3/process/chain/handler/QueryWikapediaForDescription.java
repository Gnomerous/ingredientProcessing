package org.gnomerous.ingr3.process.chain.handler;

// JAVA
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// JSOUP
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// INGR3
import org.gnomerous.ingr3.model.Ingredient;

// TODO: This class needs to be cleaned up at some point but should be good enough for initial testing
public class QueryWikapediaForDescription extends AbstractDescriptionHandler {

    @Override
    protected String lookupDescription(Ingredient ingredient) {
        try {
            return getNutrition(lookupIngredient(ingredient.getIngredientName()));
        } catch (Exception e) {
            System.out.println("Error initializing Ingredient object " + ingredient.getIngredientName());
            e.printStackTrace();
        }

        return "";
    }


    public static String getNutrition(String url) throws Exception {

        //get wiki HTML for parsing
        URL wikiTest = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(wikiTest.openStream()));

        StringBuilder sb = new StringBuilder(); //create stringbuilder to read in html from page
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();

        String html = sb.toString(); // create html string from stringbuilder
        org.jsoup.nodes.Document wiki = Jsoup.parse(html);     

        //select title for later utility - should only be one of these on all standard formatted wikipedia pages
        Elements titleElements = wiki.select("h1");
        String title = titleElements.get(0).text();

        //parse out the Nutrition/Nutrients section
        String nutritionText = "Not Found";
        Elements newEles = wiki.select("h3, h2, p");
        for (Element element : newEles) {

            //look for a description of the ingredient in specific places, i.e. after 'nutrients' or 'nutritional value' headers
            if ((element.is("h3")) && element.children().size() > 0 && element.child(0).attr("id").toLowerCase().contains("nutri")) {
                nutritionText = ((Element)element.nextSibling()).text();
            }
            if (element.is("h2") && element.children().size() > 0 && (element.child(0).attr("id").toLowerCase().contains("nutri"))) {
                nutritionText = ((Element)element.nextSibling()).text();
            }
            //if all else fails, grab the first paragraph of the page (UNDEFINED BEHAVIOR)
            if (nutritionText.equals("Not Found") && element.is("p") && (element.previousElementSibling() == null || !element.previousElementSibling().is("p")) &&
                    element.text().contains(title)) {
                System.out.println("title matched: " + title);
                nutritionText = element.text();
            }
         }

        return nutritionText;
    }

    //get wikipedia article of ingredient based on name, using wikipedia search API and article UIDs
    //TODO: actually catch exceptions more specifically
    public static String lookupIngredient(String ingredient) throws Exception {

        //craft and connect to URL using the ingredient string
        String ingredientString = ingredient.replace(" ", "%20");
        String url = "https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + ingredientString +"&srwhat=text&limit=1";
        URL ingredientPage = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(ingredientPage.openStream()));

        StringBuilder sb = new StringBuilder(); //create stringbuilder to read in html from page
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            sb.append(inputLine);
        }
        in.close();
        String html = sb.toString(); // create html string from stringbuilder
        org.jsoup.nodes.Document wiki = Jsoup.parse(html);        

        String searchText = wiki.text();

        //use regex to find the page ID
        Pattern p = Pattern.compile("pageid.: ([0-9]+)");

        try {
            Matcher m = p.matcher(searchText);
            if (m.find()) {
                //format and return page url correctly using the id we found
                return "https://en.wikipedia.org/?curid=" + m.group(1);
            }

        } catch (IllegalStateException e) {
            System.out.println("wikipedia article not found for " + ingredient);
            e.printStackTrace();
        }

        return null;
    }
}
