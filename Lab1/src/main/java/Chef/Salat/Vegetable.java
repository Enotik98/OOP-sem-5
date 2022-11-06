package Chef.Salat;

public enum Vegetable {
    CUCUMBER(15, 0.1, 0.7),
    CARROT(35, 0.2, 0.8),
    TOMATO(18, 0.2, 0.9),
    REDONION(44, 0.2, 1.4),
    ICEBERG(14, 0.1, 0.9),
    ;

    private final double calories;
    private final double fat;
    private final double protein;
    Vegetable(double calories, double fat, double protein) {
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }
}
