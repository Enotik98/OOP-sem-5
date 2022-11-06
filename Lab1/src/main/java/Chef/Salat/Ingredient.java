package Chef.Salat;

import java.util.Objects;

public class Ingredient {
    private final Vegetable ingredient;
    private double amount;

    public Ingredient(Vegetable vegetable, double amount) {
        this.ingredient = vegetable;
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be non negative");
        }
        this.amount = amount;
    }

    public Ingredient(Vegetable ingredient) {
        this.ingredient = ingredient;
        this.amount = 0;
    }

    public Vegetable getIngredient() {
        return ingredient;
    }

    public double getAmount() {
        return amount;
    }
    public double getCaloriesForIngredient(){
        return amount*(ingredient.getCalories()/100);
    }

    public String getnameVegetable(){
        return ingredient.name();
    }
    public void setAmount(double amount){
        if (amount < 0){
            throw new IllegalArgumentException("Amount must be non negative");
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return (ingredient.name() + " Nutrition Facts of 100g (Calories : " + ingredient.getCalories() + ", Fat: " + ingredient.getFat()
                + ", Protein: " + ingredient.getProtein() + ")");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Double.compare(that.amount, amount) == 0 && ingredient == that.ingredient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, amount);
    }
}
