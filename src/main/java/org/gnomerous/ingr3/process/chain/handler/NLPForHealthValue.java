package org.gnomerous.ingr3.process.chain.handler;

//GOOGLE
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

//INGR3
import org.gnomerous.ingr3.model.Ingredient;

// JAVA
import java.io.IOException;


public class NLPForHealthValue extends AbstractHealthValueHandler {


    @Override
    protected double lookupHealthValue(Ingredient ingredient) {

        // Try to get the sentiment value for the description
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            Document doc = Document.newBuilder().setContent(ingredient.getIngredientDescription()).setType(Type.PLAIN_TEXT).build();
            Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
            return sentiment.getScore();

        // Catch an IO Exception.
        } catch (IOException e) {
            //TODO: do some logging here
            System.out.println("IOexception caught while getting sentiment from description");
            e.printStackTrace();

        // Catch any other Exceptions here.
        } catch (Exception e) {
            //TODO: do some logging here
            System.out.println("exception caught while getting sentiment from description");
            e.printStackTrace();
        }

        // Return the DEFAULT_RANKING if we were unable to find a sentiment value.
        return Ingredient.DEFAULT_RANKING;
    }
}
