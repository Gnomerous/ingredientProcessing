package org.gnomerous.ingr3.process.chain.handler;

// GNOMEROUS
import org.gnomerous.ingr3.model.Ingredient;

/**
 * An AbstractDescriptionHandler is an {@link AbstractIngredientHandler} that is used
 * to retrieve a description for an {@link Ingredient}.
 * 
 * @author Marshal J. Dickey
 *
 */
public abstract class AbstractDescriptionHandler extends AbstractIngredientHandler {

    /*
     * (non-Javadoc)
     * @see org.gnomerous.ingr3.process.chain.handler.AbstractIngredientHandler#process(org.gnomerous.ingr3.model.Ingredient)
     */
    @Override
    public void process(Ingredient ingredient) {

        // Lookup the description
        String description = lookupDescription(ingredient);

        // Validate the description
        if (description == null || description == Ingredient.DEFAULT_NUTRITION_DESCRIPTION) {
            // TODO: Do some logging here
            if (hasSuccessor()) {
                // TODO: Do some logging here
                getSuccessor().process(ingredient);
            }

        } else {
            ingredient.setDescription(description);
        }
    }

    /**
     * Look up a description for an {@link Ingredient}.
     * 
     * @param ingredient
     *          The Ingredient to be used to look up the description.
     * @return the description.
     */
    protected abstract String lookupDescription(Ingredient ingredient);
}
