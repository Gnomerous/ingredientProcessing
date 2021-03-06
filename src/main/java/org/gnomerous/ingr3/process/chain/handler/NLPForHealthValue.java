package org.gnomerous.ingr3.process.chain.handler;

// GOOGLE
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;

// GNOMEROUS
import org.gnomerous.ingr3.model.Ingredient;

// JAVA
import java.io.IOException;

/**
 * An {@link AbstractHealthValueHander} designed to used Natural Language Processing
 * to generate a health value based off of the description.
 * 
 * @author Marshal J. Dickey
 *
 */
public class NLPForHealthValue extends AbstractHealthValueHandler {

    /*
     * (non-Javadoc)
     * 
     * @see org.gnomerous.ingr3.process.chain.handler.AbstractHealthValueHandler#lookupHealthValue(
     * org.gnomerous.ingr3.model.Ingredient)
     */
    @Override
    protected double lookupHealthValue(Ingredient ingredient) {

        // Try to get the sentiment value for the description
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            // Create a document that contains the description
            Document doc = Document.newBuilder().setContent(ingredient.getIngredientDescription())
                    .setType(Type.PLAIN_TEXT).build();

            // Return the sentiment value after doing some NLP analysis
            return language.analyzeSentiment(doc).getDocumentSentiment().getScore();

            // Catch an IO Exception.
        } catch (IOException e) {
            // TODO: do some logging here
            System.out.println("IOexception caught while getting sentiment from description");
            e.printStackTrace();

            // Catch any other Exceptions here.
        } catch (Exception e) {
            // TODO: do some logging here
            System.out.println("exception caught while getting sentiment from description");
            e.printStackTrace();
        }

        // Return the DEFAULT_RANKING if we were unable to find a sentiment
        // value.
        return Ingredient.DEFAULT_RANKING;
    }
}
