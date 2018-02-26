package org.gnomerous.ingr3.process.chain.handler;

// INGR3
import org.gnomerous.ingr3.model.Ingredient;

public abstract class AbstractDescriptionHandler extends AbstractIngredientHandler {

    @Override
    public void process(Ingredient ingredient) {

        // Lookup the description
        String description = lookupDescription(ingredient);

        // Validate the description
        if (description == null) { 
            //TODO:

        } else { 
            ingredient.setDescription(description);
        }
    }

    protected abstract String lookupDescription(Ingredient ingredient);
}
