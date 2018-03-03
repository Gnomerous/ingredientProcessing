package org.gnomerous.ingr3.process.chain.handler;

// INGR3
import org.gnomerous.ingr3.model.Ingredient;
import org.gnomerous.ingr3.config.ConfigLoader;
import org.gnomerous.ingr3.config.Configurable;

public abstract class AbstractIngredientHandler implements Configurable {

    private AbstractIngredientHandler successor;
    private boolean hasSuccessor = false;

    public void setSuccessor(AbstractIngredientHandler s) {
        hasSuccessor = true;
        this.successor = s;
    }

    public abstract void process(Ingredient ingredient);

    protected boolean hasSuccessor() {
        return hasSuccessor;
    }

    protected AbstractIngredientHandler getSuccessor() {
        return successor;
    }

    @Override
    public void configure(ConfigLoader loader) {
        // TODO Override
    }
}
