package org.gnomerous.ingr3.process.chain.handler;

// INGR3
import org.gnomerous.ingr3.model.Ingredient;

public abstract class IngredientHandler {

    private IngredientHandler successor;
    private boolean hasSuccessor = false;

    public void setSuccessor(IngredientHandler s) {
        hasSuccessor = true;
        this.successor = s;
    }

    public abstract void process(Ingredient ingredient);

    protected boolean hasSuccessor() { 
        return hasSuccessor;
    }

    protected IngredientHandler getSuccessor() { 
        return successor;
    }
}
