package org.gnomerous.ingr3.process.chain.handler;

// JAVA
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.function.Predicate;

// JSOUP
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// INGR3
import org.gnomerous.ingr3.model.Ingredient;

public class QueryWikipediaForDescription extends AbstractDescriptionHandler {

    // CONSTANTS

    // HTML Constants
    private static final String P_TAG = "p";
    private static final String H2_TAG = "h2";
    private static final String H3_TAG = "h3";
    private static final String ID_ATTRIBUTE = "id";
    private static final String TAGS_TO_SELECT = String.format("%s, %s, %s", H3_TAG, H2_TAG, P_TAG);

    // Wikipedia Constants
    private static final String PAGE_ID_REGEX = "pageid.: ([0-9]+)";
    private static final String ARTICLE_FROM_ID_QUERY_STRING = "/?curid=%s";
    private static final String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org";
    private static final String WIKIPEDIA_ARTICLE_LOOKUP_URL = BASE_WIKIPEDIA_URL + ARTICLE_FROM_ID_QUERY_STRING;
    private static final String SEARCH_QUERY_STRING = "/w/api.php?action=query&list=search&srsearch=%s&srwhat=text&limit=1";
    private static final String BASE_WIKIPEDIA_SEARCH_URL = BASE_WIKIPEDIA_URL + SEARCH_QUERY_STRING;

    // Filter Predicate
    private final Predicate<Element> filterPredicate = element -> {
        return (element.is(H2_TAG) == true || element.is(H3_TAG)) && element.children().size() > 0
                && element.child(0).attr(ID_ATTRIBUTE).toLowerCase().contains("nutri");
    };

    // OVERRIDES

    @Override
    protected String lookupDescription(Ingredient ingredient) {
        try {
            return getNutrition(lookupIngredient(ingredient.getIngredientName()));
        } catch (IOException e) {
            System.out.println("Error initializing Ingredient object " + ingredient.getIngredientName());
            e.printStackTrace();
        }
        return Ingredient.DEFAULT_NUTRITION_DESCRIPTION;
    }

    // CUSTOM FUNCTIONS

    private String getNutrition(String url) throws IOException {

        // Create html Document from the Ingredients wiki page
        Document wiki = Jsoup.parse(getPageDocument(url));

        // Get the elements of the wiki html document
        Elements newEles = wiki.select(TAGS_TO_SELECT);

        // Build a string of all the nutrient description for that ingredient
        String nutrientDescription = newEles.parallelStream().filter(filterPredicate)
                .map(element -> ((Element) element.nextSibling()).text()).collect(Collectors.joining("\n"));

        // Return what was found (will default to the default nutrition
        // description if nothing was found).
        return (nutrientDescription == null || nutrientDescription.trim() == "")
                ? Ingredient.DEFAULT_NUTRITION_DESCRIPTION : nutrientDescription;
    }

    private String lookupIngredient(String ingredient) throws IOException {

        // Craft the correct URL to get a wiki ID for the specific ingredient.
        String url = String.format(BASE_WIKIPEDIA_SEARCH_URL, ingredient.replaceAll(" ", "%20"));

        // Returned HTML from the given URL
        String searchText = Jsoup.parse(getPageDocument(url)).text();

        // Compile the Regex and return the result if we have a match
        Matcher regexMatcher = Pattern.compile(PAGE_ID_REGEX).matcher(searchText);
        return regexMatcher.find() ? String.format(WIKIPEDIA_ARTICLE_LOOKUP_URL, regexMatcher.group(1)) : null;
    }

    private String getPageDocument(final String stringUrl) throws IOException {
        URL url = new URL(stringUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        return in.lines().collect(Collectors.joining());
    }
}
