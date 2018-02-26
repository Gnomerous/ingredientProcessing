package org.gnomerous.ingr3.process.chain.handler;

import org.gnomerous.ingr3.model.Ingredient;

public class QueryWikipediaForDescriptionTest {

    public static void main(String[] args) {
        QueryWikipediaForDescription hvHandler = new QueryWikipediaForDescription();
        Ingredient apple = new Ingredient("Apple");
        hvHandler.process(apple);
        System.out.println(apple.getIngredientName());
        System.out.println(apple.getIngredientDescription());
        System.out.println(apple.getHealthRanking());
    }
}
