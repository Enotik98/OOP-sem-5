import Vegetables.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrayList<Vegetable> vegetables= new ArrayList<>();
        Vegetable potato=new Potato("Kartoplya","yellow",100,true);
        Vegetable cucumber= new Cucumber("ogirok","green",20,true);
        Vegetable tomato=new Tomato("pomidor","red",70,false);

        vegetables.add(potato);
        vegetables.add(cucumber);
        vegetables.add(tomato);

        Salad salad= new Salad();

        salad.setVegetables(vegetables);

        salad.showVegetables();
        salad.sortVegetables();
        salad.showVegetables();
        salad.findByRange(10,80);
        System.out.println(salad.calculateCalories());

    }
}
