package org.gnomerous.ingr3.process.chain.handler;

import org.gnomerous.ingr3.model.Ingredient;

public abstract class AbstractHealthValueHandler extends AbstractIngredientHandler {

    @Override
    public void process(Ingredient ingredient) {

        // Lookup the healthValue
        double healthValue = lookupHealthValue(ingredient);

        // Validate healthValue
        if (healthValue == Ingredient.DEFAULT_RANKING) { 
            // TODO: Do some logging here
            if (hasSuccessor()) { 
                //TODO: Do some logging here
                getSuccessor().process(ingredient);
            }

        } else { 
            ingredient.setRanking(healthValue);
        }
    }

    protected abstract double lookupHealthValue(Ingredient ingredient);
}
