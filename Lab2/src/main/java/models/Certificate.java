package models;

import java.util.Objects;

public class Certificate {
    private int number;
    private String production_date;
    private String exp_date;
    private String reg_org;

    public Certificate(int number, String production_date, String exp_date, String reg_org) {
        this.number = number;
        this.production_date = production_date;
        this.exp_date = exp_date;
        this.reg_org = reg_org;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return number == that.number && Objects.equals(production_date, that.production_date) && Objects.equals(exp_date, that.exp_date) && Objects.equals(reg_org, that.reg_org);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, production_date, exp_date, reg_org);
    }
}
