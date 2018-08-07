package org.gnomerous.ingr3.model;

/**
 * An Ingredient is an item used in the process of cooking/preparing a food
 * item. In this case, all of the Ingredients are found under the "Ingredients"
 * section of a {@link FoodLabel} for all food items sold in stores such as a
 * Grocery Store.
 * 
 * @author Marshal J. Dickey
 */
public class Ingredient {

    // CONSTANTS

    /** The default value for {@link #ingredientDescription}. */
    public static final String DEFAULT_NUTRITION_DESCRIPTION = "Not Found";

    /** The default value for {@link #healthRanking}. */
    public static final double DEFAULT_RANKING = -5.0;

    // CLASS ATTRIBUTES

    private double healthRanking;
    private String ingredientName;
    private String ingredientDescription;

    // CONSTRUCTORS

    /**
     * The Constructor for an {@link Ingredient}.
     * 
     * @param name
     *          The name of the Ingredient.
     */
    public Ingredient(final String name) {
        this(name, DEFAULT_NUTRITION_DESCRIPTION, DEFAULT_RANKING);
    }

    /**
     * The Constructor for an {@link Ingredient}.
     * 
     * @param name
     *          The name of the Ingredient.
     * @param description
     *          The description of the Ingredient.
     */
    Ingredient(final String name, final String description) {
        this(name, description, DEFAULT_RANKING);
    }

    /**
     * The Constructor for an {@link Ingredient}.
     * 
     * @param name
     *          The name of the Ingredient.
     * @param description
     *          The description of the Ingredient.
     * @param ranking
     *          The ranking of the Ingredient.
     */
    Ingredient(final String name, final String description, final double ranking) {
        setName(name);
        setRanking(ranking);
        setDescription(description);
    }

    // MODIFIERS

    /**
     * Set the name of the {@link Ingredient}.
     * 
     * @param name
     *          The name of the Ingredient.
     */
    public void setName(final String name) {
        this.ingredientName = name;
    }

    /**
     * Set the description of the {@link Ingredient}.
     * 
     * @param description
     *          The description of the Ingredient.
     */
    public void setDescription(final String description) {
        this.ingredientDescription = description;
    }

    /**
     * Set the ranking of the {@link Ingredient}.
     * 
     * @param ranking
     *          The ranking of the Ingredient.
     */
    public void setRanking(final double ranking) {
        this.healthRanking = ranking;
    }

    // GETTERS

    /**
     * @return the Ingredient name.
     */
    public String getIngredientName() {
        return new String(ingredientName);
    }

    /**
     * @return the Ingredient description.
     */
    public String getIngredientDescription() {
        return new String(ingredientDescription);
    }

    /**
     * @return the Ingredient's health ranking.
     */
    public double getHealthRanking() {
        return new Double(healthRanking);
    }
}
