package dukepro.expenses;

import dukepro.StorableObjects;
import dukepro.exceptions.DukeException;
import dukepro.handlers.Decoder;

import java.time.LocalDate;

/**
 * Class for Expenses
 */
public class Expense extends StorableObjects {
    String name;
    int amount;
    LocalDate localDate;

    /**
     * Constructor for Expense class
     *
     * @param name the name of the expense.
     * @param amount the amount spent.
     * @param localDate the date of the expense.
     * @return An Expense
     */
    public Expense(String name, int amount, LocalDate localDate) {
        this.name = name;
        this.amount = amount;
        this.localDate = localDate;
    }

    public String fileForm() {
        return name + "," + amount + "," + localDate;
    }

    public boolean getMatching(String search) {
        return true;
    }

    public boolean compareDate(LocalDate localDate) {
        return true;
    }

    /**
     * Returns a string representing user's
     * search input.
     *
     * @param ld A LocalDate.
     * @return A boolean.
     */
    public boolean matchDate(LocalDate ld) {
        return ld.equals(localDate);
    }

    /**
     * Returns a string representing an Expense
     *
     * @return A String.
     */
    @Override
    public String toString() {
        String ret = name + ": $" + amount + " at " + localDate;
        return ret;
    }
}
