package dukeprogram.facilities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import dukeprogram.userinterface.WidgetLoanLabel;

/**
 * A loan object stores the current loans to a person in the real world
 */
public class Loan implements Serializable {

    @JsonProperty("creditor")
    private String creditor;
    @JsonProperty("moneyOwed")
    private double moneyOwed;

    /**
     * Creates a new Loan object
     * @param creditor the person whom the money is owed to
     * @param moneyOwed the amount of money owed
     */
    public Loan(String creditor, double moneyOwed) {
        this.creditor = creditor;
        this.moneyOwed = moneyOwed;
    }

    private Loan() {

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

    @JsonIgnore
    public String getCreditorName() {
        return creditor;
    }

    @JsonIgnore
    public double getAmount() {
        return moneyOwed;
    }

    @Override
    public String toString() {
        String representation = "> " + creditor + "\n\t\t| ";

        if (moneyOwed > 0) {
            representation += "\tOwed $" + moneyOwed;
        } else {
            representation += "\tLent $" + -moneyOwed;
        }

        return representation;
    }

    /**
     * Creates a WidgetLoanLabel for use in the dialog bubbles
     * @return a widget loan label
     */
    public WidgetLoanLabel makeWidget() {
        return new WidgetLoanLabel(creditor, moneyOwed);
    }
}
