package Testing;

import org.junit.jupiter.api.Test;

import dukeprogram.facilities.Loan;
import dukeprogram.facilities.LoanCollection;
import exceptions.KeyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoansTests {

    /**
     * Checks if creation of loans collection is correct
     */
    @Test
    public void loans_createLoansCollection() {
        LoanCollection lc = new LoanCollection();
        assertEquals(0, lc.getSize());
    }

    /**
     * Checks if additions of loans is correct
     */
    @Test
    public void loans_addLoans() {
        LoanCollection lc = new LoanCollection();
        lc.add("Janice", 500);
        lc.add("Paul", 300);

        assertEquals(2, lc.getSize());

        Loan janiceLoan;
        Loan paulLoan;
        try {
            janiceLoan = lc.get("Janice");
        } catch (KeyNotFoundException e) {
            janiceLoan = null;
        }

        try {
            paulLoan = lc.get("Paul");
        } catch (KeyNotFoundException e) {
            paulLoan = null;
        }
        assertNotNull(janiceLoan);
        assertNotNull(paulLoan);

        assertEquals(500, janiceLoan.getAmount());
        lc.add("Janice", 378);
        assertEquals(878, janiceLoan.getAmount());
        lc.add("Janice", -1292);
        assertEquals(-414, janiceLoan.getAmount());

        assertEquals(300, paulLoan.getAmount());
    }

    /**
     * Checks if the removal of loans is correct
     */
    @Test
    public void loans_removeLoan() {
        LoanCollection lc = new LoanCollection();

        lc.add("Paul", 212);
        lc.add("Isabelle", -341);
        lc.add("Jeremy", 139);
        lc.add("Jerome", 2333);

        assertEquals(4, lc.getSize());
        Loan noLoan = lc.remove("Kyle");
        assertNull(noLoan);
        assertEquals(4, lc.getSize());

        Loan isabellaLoan = lc.remove("Isabelle");
        assertEquals(-341, isabellaLoan.getAmount());
    }
}
