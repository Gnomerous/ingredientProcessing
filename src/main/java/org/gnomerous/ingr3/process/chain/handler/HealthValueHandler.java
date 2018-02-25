package org.gnomerous.ingr3.process.chain.handler;

import org.gnomerous.ingr3.model.Ingredient;

public abstract class HealthValueHandler extends IngredientHandler {

    @Override
    public void process(Ingredient ingredient) {

        // Lookup the healthValue
        double healthValue = lookupHealthValue(ingredient.getIngredientName());

        // Validate healthValue
        if (healthValue == Ingredient.DEFAULT_RANKING) { 
            // TODO:

        } else { 
            ingredient.setRanking(healthValue);
        }
    }

    protected abstract double lookupHealthValue(String name);
}
