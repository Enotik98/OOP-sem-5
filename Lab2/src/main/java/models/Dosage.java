package models;

import java.util.Objects;

public class Dosage {
    private int dose;
    private double frq_hours;

    public Dosage(int dose, double frq_hours) {
        this.dose = dose;
        this.frq_hours = frq_hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dosage dosage = (Dosage) o;
        return dose == dosage.dose && Double.compare(dosage.frq_hours, frq_hours) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dose, frq_hours);
    }
}
