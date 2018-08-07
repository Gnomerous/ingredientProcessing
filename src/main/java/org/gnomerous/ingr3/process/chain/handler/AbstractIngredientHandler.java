package org.gnomerous.ingr3.process.chain.handler;

// GNOMEROUS
import org.gnomerous.ingr3.model.Ingredient;
import org.gnomerous.ingr3.config.ConfigLoader.ConfigProperties;
import org.gnomerous.ingr3.config.Configurable;

/**
 * An AbstractIngredientHandler takes an {@link Ingredient} and is used to 
 * populate a specific value(s) for that Ingredient.
 * 
 * @author Marshal J. Dickey
 *
 */
public abstract class AbstractIngredientHandler implements Configurable {

    // CLASS ATTRIBUTES
    private AbstractIngredientHandler successor;
    private boolean hasSuccessor = false;

    /**
     * Set a successor that can be used 
     * @param s
     */
    public void setSuccessor(AbstractIngredientHandler s) {
        hasSuccessor = true;
        this.successor = s;
    }

    /**
     * The logic used to process an {@link Ingredient}.  Each {@link AbstractIngredientHandler}
     * will perform something unique on the {@link Ingredient}.
     * 
     * @param ingredient
     *          The Ingredient to process.
     */
    public abstract void process(Ingredient ingredient);

    /**
     * Determine if the {@link AbstractIngredientHandler} has a successor or not.
     * 
     * @return True if a successor was set.  False otherwise.
     */
    protected boolean hasSuccessor() {
        return hasSuccessor;
    }

    /**
     * Return another {@link AbstractIngredientHandler}.  Different Ingredient Handlers
     * use these successors in different ways. Make sure to check the 
     * {@link #process(Ingredient)} logic to see how they are being used.
     * 
     * @return
     *      An {@link AbstractIngredientHandler} that is this one's successor. 
     */
    protected AbstractIngredientHandler getSuccessor() {
        return successor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gnomerous.ingr3.config.Configurable#configure(org.gnomerous.ingr3.config.ConfigLoader.ConfigProperties)
     */
    @Override
    public void configure(ConfigProperties loader) {
        // TODO Override
    }
}
