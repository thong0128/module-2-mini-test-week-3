package material;

import java.time.LocalDate;

public class Meat extends Material implements Discount {
    private double weight;

    public Meat() {
    }

    public Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return getCost() * weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        LocalDate today = LocalDate.now();
        if (today.plusDays(5).isAfter(getExpiryDate())) {
            return getAmount() * Discount.DISCOUNT30;
        }
        return getAmount() * Discount.DISCOUNT10;
    }

    @Override
    public String toString() {
        return "Meat{" +
                "weight=" + weight +
                "} " + super.toString();
    }
}
