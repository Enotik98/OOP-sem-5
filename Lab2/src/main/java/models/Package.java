package models;

import java.util.Objects;

public class Package {
    private String type;
    private int qty ;
    private double price;

    public Package(String type, int qty, double price) {
        this.type = type;
        this.qty = qty;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return qty == aPackage.qty && Double.compare(aPackage.price, price) == 0 && Objects.equals(type, aPackage.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, qty, price);
    }
}
