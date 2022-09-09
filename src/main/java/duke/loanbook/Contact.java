package duke.loanbook;

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
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.isOwe = isOwe;
    }

    @Override
    public String toString() {
        String amountDescription = this.isOwe
                ? "You owe him " + this.amount
                : "He/She owes you " + this.amount;
        return name + ", " + phoneNumber + "\n" + "   " + amountDescription;
    }
}
