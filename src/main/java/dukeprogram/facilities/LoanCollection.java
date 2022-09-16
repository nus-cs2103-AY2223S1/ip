package dukeprogram.facilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import dukeprogram.storage.SaveManager;
import exceptions.KeyNotFoundException;

public class LoanCollection implements Serializable {
    private final Map<Person, Loan> loans = new HashMap<>();
    private final Map<String, Person> uniquePersonNames = new HashMap<>();

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
    public Loan[] getAllLoans() {
        return loans.values().toArray(new Loan[0]);
    }


    /**
     * Retrieves the size of all the stored task lists
     * @return the size of all task lists
     */
    public int getSize() {
        return loans.size();
    }

    /**
     * Adds an amount of owed money to a creditor.
     * This operation will save the loan collection afterwards.
     * @param creditor the person the money is owed to
     * @param amountOwedToAdd the amount of owed money to be added
     * @return
     */
    public void add(Person creditor, double amountOwedToAdd) {
        if (loans.containsKey(creditor)) {
            loans.get(creditor).addAmountOwed(amountOwedToAdd);
        } else {
            loans.put(creditor, new Loan(creditor, amountOwedToAdd));
        }
        SaveManager.save("loanCollection", this);
    }

    /**
     * Adds an amount of owed money to a creditor
     * @param creditorName the name of the person the money is owed to
     * @param amountOwedToAdd the amount of owed money to be added
     * @return
     */
    public void add(String creditorName, double amountOwedToAdd) {
        Person creditor;
        if (uniquePersonNames.containsKey(creditorName)) {
            creditor = uniquePersonNames.get(creditorName);
        } else {
            creditor = new Person(creditorName);
            uniquePersonNames.put(creditorName, creditor);
        }
        add(creditor, amountOwedToAdd);
    }

    /**
     * Clears the entire loan collection completely
     */
    public void clear() {
        loans.clear();
        SaveManager.save("loanCollection", this);
    }

    public Loan get(Person creditor) {
        return loans.get(creditor);
    }

    public Loan get(String creditorName) throws KeyNotFoundException {
        if (!uniquePersonNames.containsKey(creditorName)) {
            throw new KeyNotFoundException(creditorName, "loans");
        }

        return loans.get(uniquePersonNames.get(creditorName));
    }

    /**
     * Removes a person and the corresponding loan from the loan list
     * @param creditor the person and attached loan to remove
     * @return the task that was removed if the index was valid, otherwise null
     */
    public Loan remove(Person creditor) {
        if (creditor == null) {
            return null;
        }

        uniquePersonNames.remove(creditor.getName());
        return loans.remove(creditor);
    }

    /**
     * Removes a person and the corresponding loan from the loan list
     * @param creditorName the person's name and attached loan to remove
     * @return the task that was removed if the index was valid, otherwise null
     */
    public Loan remove(String creditorName) {
        return remove(uniquePersonNames.remove(creditorName));
    }
}
