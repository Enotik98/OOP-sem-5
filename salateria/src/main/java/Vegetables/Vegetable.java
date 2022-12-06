package Vegetables;

public abstract class Vegetable {

    private String name;
    private String color;
    private int calories;
    private boolean fresh;

    public Vegetable(String name, String color, int calories, boolean fresh) {
        this.name = name;
        this.color = color;
        this.calories = calories;
        this.fresh = fresh;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor() {
        this.color = color;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories() {
        this.calories = calories;
    }

    public boolean getFresh() {
        return fresh;
    }

    public void setFresh() {
        this.fresh = fresh;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "name= " + name + " color= " + color + " calories= " + calories + " fresh= " + fresh;
    }
}
