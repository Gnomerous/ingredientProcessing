package org.gnomerous.ingr3.model;


/**
 * An Ingredient is an item used in the process of cooking/preparing a food item.  In this case, all of the
 * Ingredients are found under the "Ingredients" section of a {@link FoodLabel} for all food items sold in 
 * stores such as a Grocery Store.
 * @author Marshal J. Dickey
 */
public class Ingredient {



    // CONSTANTS

    /** The default value for {@link #ingredientDescription}. */
    public static final String EMPTY_DESCRIPTION = "";

    /** The default value for {@link #healthRanking}. */
    public static final double DEFAULT_RANKING = -5.0;



    // CLASS ATTRIBUTES

    private double healthRanking;
    private String ingredientName;
    private String ingredientDescription;



    // CONSTRUCTORS

    public Ingredient(final String name) { 
        this(name, EMPTY_DESCRIPTION, DEFAULT_RANKING);
    }
    
    Ingredient(final String name, final String description) {
        this(name, description, DEFAULT_RANKING);
    }
    
    Ingredient(final String name, final String description, final double ranking) {
        setName(name);
        setRanking(ranking);
        setDescription(description);
    }



    // MODIFIERS

    public void setName(final String name) { 
        this.ingredientName = name;
    }

    public void setDescription(final String description) { 
        this.ingredientDescription = description;
    }

    public void setRanking(final double ranking) {
        this.healthRanking = ranking;
    }



    // GETTERS

    public String getIngredientName() { 
        return new String(ingredientName);
    }

    public String getIngredientDescription() { 
        return new String(ingredientDescription);
    }

    public double getHealthRanking() { 
        return new Double(healthRanking);
    }
}
