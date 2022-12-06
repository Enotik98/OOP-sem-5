package Vegetables;

import java.util.ArrayList;

public class Salad {
    private ArrayList<Vegetable> vegetables;

    public Salad() {
        this.vegetables = new ArrayList<>();
    }

    public void setVegetables(ArrayList<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public ArrayList<Vegetable> getVegetables() {
        return vegetables;
    }

    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }

    public void deleteVegetable(String name) {
        for (int i = 0; i < vegetables.size(); i++) {
            Vegetable v1 = vegetables.get(i);
            if (v1.getName().equals(name)) {
                vegetables.remove(i);
                return;
            }
        }
    }

    public void sortVegetables() {
        if (vegetables == null) return;

        for (int i = 0; i < vegetables.size(); i++) {
            for (int j = 0; j < vegetables.size() - 1 - i; j++) {
                if (vegetables.get(j).getCalories() > vegetables.get(j + 1).getCalories()) {
                    Vegetable temp = vegetables.get(j);
                    vegetables.set(j, vegetables.get(j + 1));
                    vegetables.set(j + 1, temp);
                }
            }
        }
    }

    public void showVegetables() {
        for (Vegetable vegetable : vegetables) {
            System.out.println(vegetable.toString());
        }
        System.out.println();
    }

    public void showVegetables(ArrayList<Vegetable> vegetables) {
        for (Vegetable vegetable : vegetables) {
            System.out.println(vegetable.toString());
        }
        System.out.println();
    }

    public ArrayList<Vegetable> findByRange(int min, int max) {
        if (min >= max) return null;

        ArrayList<Vegetable> result = new ArrayList<>();
        for (Vegetable vegetable : vegetables) {
            if (vegetable.getCalories() <= max && vegetable.getCalories() >= min) {
                result.add(vegetable);
            }
        }
        showVegetables(result);
        return result;
    }

    public int calculateCalories() {
        int totalCalories = 0;
        for (Vegetable vegetable : vegetables) {
            totalCalories += vegetable.getCalories();
        }
        return totalCalories;
    }
}
