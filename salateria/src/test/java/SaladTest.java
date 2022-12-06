import Vegetables.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SaladTest {
    ArrayList<Vegetable> vegetables = new ArrayList<>();
    Salad salad = new Salad();

    @Before
    public void setUp() {

        Vegetable potato = new Potato("Kartoplya", "yellow", 20, true);
        Vegetable cucumber = new Cucumber("ogirok", "green", 100, true);
        Vegetable tomato = new Tomato("pomidor", "red", 70, false);
        vegetables.add(potato);
        vegetables.add(cucumber);
        vegetables.add(tomato);

        salad.setVegetables(vegetables);
    }

    @Test
    public void sortSuccess_TEST(){
        salad.sortVegetables();;
        for(int i=0;i<vegetables.size()-1;i++){
            Assert.assertFalse(vegetables.get(i).getCalories()>vegetables.get(i+1).getCalories());
            Assert.assertEquals(salad.calculateCalories(),190);
        }
        ArrayList<Vegetable> result=salad.findByRange(10,60);
        for(int i=0;i<result.size();i++){
            Assert.assertTrue(result.get(i).getCalories()<60&&result.get(i).getCalories()>10);
        }
    }
}
