package dukeprogram.facilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

/**
 * A LoanCollection represents a collection of loans associated with creditors
 */
public class LoanCollection implements Serializable {
    private final Map<String, Loan> loans = new HashMap<>();

    /**
     * Initialises the LoanCollection saved
     */
    public static LoanCollection loadLoanCollection() {
        LoanCollection current;

        try {
            current = SaveManager.load("loanCollection");
        } catch (KeyNotFoundException e) {
            current = new LoanCollection();
            SaveManager.save("loanCollection", current);
        }

        return current;
    }


    /**
     * Gets all the recorded loans
     * @return an array of Loan objects
     */
    @JsonIgnore
    public Loan[] getAllLoans() {
        return loans.values().toArray(new Loan[0]);
    }


    /**
     * Retrieves the size of all the stored task lists
     * @return the size of all task lists
     */
    @JsonIgnore
    public int getSize() {
        return loans.size();
    }

    /**
     * Adds an amount of owed money to a creditor
     * @param creditorName the name of the person the money is owed to
     * @param amountOwedToAdd the amount of owed money to be added
     * @return
     */
    public void add(String creditorName, double amountOwedToAdd) {
        if (!loans.containsKey(creditorName)) {
            loans.put(creditorName, new Loan(creditorName, amountOwedToAdd));
        } else {
            Loan existingLoan = loans.get(creditorName);
            existingLoan.addAmountOwed(amountOwedToAdd);
        }
    }

    /**
     * Clears the entire loan collection completely
     */
    public void clear() {
        loans.clear();
        SaveManager.save("loanCollection", this);
    }

    public Loan get(String creditorName) throws KeyNotFoundException {
        if (!loans.containsKey(creditorName)) {
            throw new KeyNotFoundException(creditorName, "loans");
        }

        return loans.get(creditorName);
    }

    /**
     * Removes a person and the corresponding loan from the loan list
     * @param creditorName the person's name and attached loan to remove
     * @return the task that was removed if the index was valid, otherwise null
     */
    public Loan remove(String creditorName) {
        return loans.remove(creditorName);
    }

    /**
     * Checks to see if the name of the creditor exists in the loan collection
     * @param name name of the creditor
     * @return whether the name exists as a creditor's name
     */
    public boolean containsKey(String name) {
        return loans.containsKey(name);
    }
}
