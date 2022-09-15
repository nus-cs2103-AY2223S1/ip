package dukepro.expenses;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for ExpenseCalculator
 */
public class ExpenseCalculator {

    /**
     * Returns sum of all items in an ArrayList
     * of Expenses.
     *
     * @param expenses The ArrayList of expenses.
     * @return An int.
     */
    public static double sumArrayList(ArrayList<? extends Expense> expenses) {
        double sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum = sum + expenses.get(i).spent();
        }
        return sum;
    }

    /**
     * Returns sum of all items in an ArrayList
     * of Expenses, for that particular date.
     *
     * @param expenses The ArrayList of expenses.
     * @param localDate The LocalDate.
     * @return An int.
     */
    public static double spentDay(ArrayList<? extends Expense> expenses, LocalDate localDate) {
        double sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).compareDate(localDate)) {
                sum = sum + expenses.get(i).spent();
            }
        }
        return sum;
    }
}
