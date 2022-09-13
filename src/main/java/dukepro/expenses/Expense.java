package dukepro.expenses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dukepro.StorableObjects;

/**
 * Class for Expense
 */
public class Expense extends StorableObjects {
    private String name;
    private int amount;
    private LocalDate localDate;

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

    /**
     * Returns String to be written to
     * file for storage.
     *
     * @return A String
     */
    public String toFileForm() {
        return name + "," + amount + "," + localDate;
    }

    /**
     * Returns whether name of expense matches
     * search query.
     *
     * @param search A String representing search query.
     * @return A boolean
     */
    public boolean getMatching(String search) {
        return name.contains(search);
    }

    /**
     * Returns whether the date of this expense
     * matches the date in query.
     *
     * @param localDate The LocalDate query.
     * @return A boolean
     */
    public boolean compareDate(LocalDate localDate) {
        return localDate.equals(this.localDate);
    }

    /**
     * Returns cost of this expense.
     *
     * @return An int.
     */
    public int spent() {
        return this.amount;
    }

    /**
     * Returns a string representing an Expense.
     *
     * @return A String.
     */
    @Override
    public String toString() {
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String ret = name + ": $" + amount + " at " + date;
        return ret;
    }
}
