package duke.loanbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;

/**
 * Represents a Contact in the loan book.
 *
 * @author Elgin
 */
public class Contact {
    private String name;
    private String phoneNumber;
    private double amount;
    private boolean isOwe;

    /**
     * Constructor of a Contact.
     *
     * @param name The name of the contact.
     * @param phoneNumber The phone number of contact.
     * @param amount The amount you owe this contact.
     * @param isOwe True if you owe this contact money, false otherwise.
     */
    public Contact(String name, String phoneNumber, double amount, boolean isOwe) {
        // Checks that phone number provided is of a valid pattern (Singapore number).
        Pattern pattern = Pattern.compile("^[689][0-9]{7}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new DukeException("Please provide a valid phone number!");
        }

        if (amount < 0) {
            throw new DukeException("Amount cannot be negative!");
        }

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.isOwe = isOwe;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public double getAmount() {
        return this.amount;
    }

    public String isOwe() {
        return isOwe ? "1" : "0";
    }

    @Override
    public String toString() {
        String amountDescription = this.isOwe
                ? "You owe him $" + this.amount
                : "He/She owes you $" + this.amount;
        return name + ", " + phoneNumber + "\n" + "   " + amountDescription;
    }
}
