package org.gnomerous.ingr3.process.chain;

// JAVA
import java.util.ArrayList;
import java.util.List;

// INGR3
import org.gnomerous.ingr3.model.Ingredient;
import org.gnomerous.ingr3.process.chain.handler.AbstractIngredientHandler;

public class ChainManager {

    private List<AbstractIngredientHandler> chain = new ArrayList<>();
    
    public void appendToChain(AbstractIngredientHandler ih) { 
        this.chain.add(ih);
    }

    public void clearChain() { 
        chain.clear();
    }

    public boolean runChainOnIngredient(Ingredient ingredient) { 
        chain.forEach(ih -> ih.process(ingredient));
        return true;
    }
}
