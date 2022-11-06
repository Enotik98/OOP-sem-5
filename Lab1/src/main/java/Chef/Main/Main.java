package Chef.Main;

import Chef.Salat.Ingredient;
import Chef.Salat.Salad;

import static Chef.Salat.Vegetable.*;

public class Main {

    public static void main(String[] args) {
        Salad salad =new Salad("salad", new Ingredient[]{new Ingredient(CUCUMBER, 100), new Ingredient(REDONION, 75),new Ingredient(ICEBERG, 50),new Ingredient(TOMATO, 200),new Ingredient(CARROT, 10)});

        salad.showRecipe();
        System.out.println("Total Calories " + salad.totalCalories());
        System.out.println();
        salad.sortByCalories();
        salad.sortByName();
        salad.showRecipe();
        salad.foundInDiapasonCalories(15, 35);
    }
}
