package org.gnomerous.ingr3.process.chain.handler;

import org.gnomerous.ingr3.model.Ingredient;

public class NLPForHealthValueTest {

    public static void main(String[] args) {
        NLPForHealthValue hvHandler = new NLPForHealthValue();
        Ingredient apple = new Ingredient("Apple");
        apple.setDescription("Apples are very good for the body and are packed with vitamins");
        hvHandler.process(apple);
        System.out.println(apple.getIngredientName());
        System.out.println(apple.getIngredientDescription());
        System.out.println(apple.getHealthRanking());
    }
}
