package org.gnomerous.ingr3.process.chain;

// JAVA
import java.util.ArrayList;
import java.util.List;

// GNOMEROUS
import org.gnomerous.ingr3.model.Ingredient;
import org.gnomerous.ingr3.config.ConfigLoader.ConfigProperties;
import org.gnomerous.ingr3.config.Configurable;
import org.gnomerous.ingr3.process.chain.handler.AbstractIngredientHandler;

/**
 * A ChainManager is used to link different {@link AbstractIngredientHandler}s
 * together to get the different values for all of the Ingredients.
 * 
 * @author Marshal J. Dickey
 *
 */
public class ChainManager implements Configurable {

    private List<AbstractIngredientHandler> chain = new ArrayList<>();

    /**
     * Append an {@link AbstractIngredientHandler} to the chain.
     * 
     * @param ih
     *          The {@link AbstractIngredientHandler} to add to the chain.
     */
    public void appendToChain(AbstractIngredientHandler ih) {
        this.chain.add(ih);
    }

    /**
     * Clear the chain.
     */
    public void clearChain() {
        chain.clear();
    }

    /**
     * Run the entire chain of {@link AbstractIngredientHandler}s on a 
     * given {@link Ingredient} to populate all of the values.
     * 
     * @param ingredient
     *          The {@link Ingredient} that will be populated.
     */
    public void runChainOnIngredient(Ingredient ingredient) {
        chain.forEach(ih -> ih.process(ingredient));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.gnomerous.ingr3.config.Configurable#configure(org.gnomerous.ingr3.config.ConfigLoader.ConfigProperties)
     */
    @Override
    public void configure(ConfigProperties loader) {
        // TODO: Nothing to do here yet.
    }
}
