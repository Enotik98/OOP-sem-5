import Chef.Salat.Ingredient;
import Chef.Salat.Salad;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import static Chef.Salat.Vegetable.*;

public class IngredientTest {
    @Test
    public void SaladTotalCalories_Test(){
        Salad salad =new Salad("salad", new Ingredient[]{new Ingredient(CUCUMBER, 100), new Ingredient(CARROT, 10)});
        Assert.assertEquals(18.5, salad.totalCalories());
    }

    @Test
    public void SaladSortByName_Test(){
        Salad salad =new Salad("salad", new Ingredient[]{new Ingredient(CUCUMBER, 100), new Ingredient(REDONION, 75),new Ingredient(ICEBERG, 50),new Ingredient(TOMATO, 200),new Ingredient(CARROT, 10)});
        Ingredient[] sortIngredients = new Ingredient[]{new Ingredient(CARROT, 10),new Ingredient(CUCUMBER, 100), new Ingredient(ICEBERG, 50), new Ingredient(REDONION, 75), new Ingredient(TOMATO, 200)};
        salad.sortByName();
        Arrays.equals(sortIngredients, salad.getIngredients());
    }
    @Test
    public void SaladSortByCalories_Test(){
        Salad salad =new Salad("salad", new Ingredient[]{new Ingredient(CUCUMBER, 100), new Ingredient(REDONION, 75),new Ingredient(ICEBERG, 50),new Ingredient(TOMATO, 200),new Ingredient(CARROT, 10)});
        Ingredient[] sortByCalories = new Ingredient[]{new Ingredient(CARROT, 10), new Ingredient(ICEBERG, 50),new Ingredient(CUCUMBER, 100),new Ingredient(REDONION, 75),new Ingredient(TOMATO, 200)};
        salad.sortByCalories();
        Arrays.equals(sortByCalories, salad.getIngredients());
    }
    @Test
    public void SaladDiapason(){
        Salad salad =new Salad("salad", new Ingredient[]{new Ingredient(CUCUMBER, 100), new Ingredient(REDONION, 75),new Ingredient(ICEBERG, 50),new Ingredient(TOMATO, 200),new Ingredient(CARROT, 10)});
        List<Ingredient> result = new ArrayList<>();
        result.add(new Ingredient(CUCUMBER, 100));
        result.add(new Ingredient(REDONION, 75));
        List<Ingredient> saladDiapason = salad.foundInDiapasonCalories(15, 35);
        Assert.assertEquals(result, saladDiapason);
    }
}
