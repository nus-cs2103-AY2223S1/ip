package dukeprogram.facilities;

import java.io.Serializable;

/**
 * A loan object stores the current loans to a person in the real world
 */
public class Loan implements Serializable {

    private final Person creditor;
    private double moneyOwed;

    /**
     * Creates a new Loan object
     * @param creditor the person whom the money is owed to
     * @param moneyOwed the amount of money owed
     */
    public Loan(Person creditor, double moneyOwed) {
        this.creditor = creditor;
        this.moneyOwed = moneyOwed;
    }

    /**
     * Adds money to amount owed to the creditor
     * @param amount the amount of money to add
     */
    public void addAmountOwed(double amount) {
        moneyOwed += amount;
    }

    /**
     * Decreases money to amount owed to the creditor
     * @param amount the amount of money to decrease.
     *               If the value is negative, it is equivalent to calling addAmountOwed
     */
    public void decreaseAmountOwed(double amount) {
        addAmountOwed(-amount);
    }

    public Person getCreditor() {
        return creditor;
    }

    public double getAmount() {
        return moneyOwed;
    }

    @Override
    public String toString() {
        String representation = "> " + creditor.getName() + "\t\t| ";

        if (moneyOwed > 0) {
            representation += "\tOwed $" + moneyOwed;
        } else {
            representation += "\tLent $" + -moneyOwed;
        }

        return representation;
    }
}
