package Chef.Salat;

import java.util.ArrayList;
import java.util.List;

public class Salad {
    private String name;
    private final Ingredient[] ingredients;

    public Salad(String name, Ingredient[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public void showRecipe() {
        if (ingredients.length == 0) {
            System.out.println("You haven't added any ingredients");
            return;
        }
        System.out.println("Salad: " + name);
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.toString() + " - " + ingredient.getAmount());
        }
    }

    public double totalCalories() {
        double sum = 0;
        if (ingredients.length == 0) {
            System.out.println("You haven't added any ingredients");
            return sum;
        }
        System.out.println("Salad: " + name);
        for (Ingredient ing : ingredients) {
            sum += ing.getCaloriesForIngredient();
        }
        return sum;
    }

    public void sortByCalories() {
        for (int i = 0; i < ingredients.length - 1; i++) {
            if (ingredients[i].getCaloriesForIngredient() > ingredients[i + 1].getCaloriesForIngredient()) {
                Ingredient temp = ingredients[i];
                ingredients[i] = ingredients[i + 1];
                ingredients[i + 1] = temp;
                i = -1;
            }
        }
        showRecipe();
    }

    public void sortByName() {
        for (int i = 0; i < ingredients.length - 1; i++) {
            String ing1 = ingredients[i].getIngredient().name();
            String ing2 = ingredients[i+1].getIngredient().name();
            if (ing1.compareTo(ing2) > 0){
                Ingredient temp = ingredients[i];
                ingredients[i] = ingredients[i + 1];
                ingredients[i + 1] = temp;
                i = -1;
            }

        }
        System.out.println("sort by name");
        showRecipe();
    }
    public List<Ingredient> foundInDiapasonCalories(double start, double end){
        List<Ingredient> res = new ArrayList<>();
        for (Ingredient ing : ingredients) {
            if (start <= ing.getCaloriesForIngredient() && ing.getCaloriesForIngredient() <= end){
                res.add(ing);
            }
        }
//        for (Ingredient i: res){
//            System.out.println(i.toString());
//        }
        return res;
    }
}
