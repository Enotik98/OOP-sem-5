import Vegetables.Salad;
import Vegetables.Vegetable;

import java.util.List;

public class Show {


    public void showVegetables(List<Vegetable> vegetables) {
        for (Vegetable vegetable : vegetables) {
            System.out.println(vegetable.toString());
        }
        System.out.println();
    }
}
