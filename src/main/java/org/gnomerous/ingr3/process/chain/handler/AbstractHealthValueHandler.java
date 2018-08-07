package org.gnomerous.ingr3.process.chain.handler;

// GNOMEROUS
import org.gnomerous.ingr3.model.Ingredient;

/**
 * an AbstractHealthValueHandler is an {@link AbstractIngredientHandler} that is used
 * to look up the health value of an {@link Ingredient}.
 * 
 * @author Marshal J. Dickey
 *
 */
public abstract class AbstractHealthValueHandler extends AbstractIngredientHandler {

    /*
     * (non-Javadoc)
     * 
     * @see org.gnomerous.ingr3.process.chain.handler.AbstractIngredientHandler#process(org.gnomerous.ingr3.model.Ingredient)
     */
    @Override
    public void process(Ingredient ingredient) {

        // Lookup the healthValue
        double healthValue = lookupHealthValue(ingredient);

        // Validate healthValue
        if (healthValue == Ingredient.DEFAULT_RANKING) {
            // TODO: Do some logging here
            if (hasSuccessor()) {
                // TODO: Do some logging here
                getSuccessor().process(ingredient);
            }

        } else {
            ingredient.setRanking(healthValue);
        }
    }

    /**
     * Look up a health value for an {@link Ingredient}.
     * 
     * @param ingredient
     *          The Ingredient to be used to look up the health value.
     * @return the health value for the Ingredient.
     */
    protected abstract double lookupHealthValue(Ingredient ingredient);
}
