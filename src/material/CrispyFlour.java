package material;

import java.time.LocalDate;

public class CrispyFlour extends Material implements Discount {
    private double quantity;

    public CrispyFlour() {
    }

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, double quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return quantity * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        LocalDate today = LocalDate.now();
        return today.plusMonths(2).isAfter(getExpiryDate()) ? getAmount() * Discount.DISCOUNT40
                : today.plusMonths(4).isAfter(getExpiryDate()) ? getAmount() * Discount.DISCOUNT20
                : getAmount() * Discount.DISCOUNT05;
    }

    @Override
    public String toString() {
        return "CrispyFlour{" +
                "quantity=" + quantity +
                "} " + super.toString();
    }
}
