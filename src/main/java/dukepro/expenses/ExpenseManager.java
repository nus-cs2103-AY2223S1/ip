package dukepro.expenses;

import java.util.ArrayList;

public class ExpenseManager {
    private ArrayList<Expense> expenses = new ArrayList<>();

    /**
     * Adds an expense
     *
     * @param expense An Expense.
     * @return A String.
     */
    public String addExpense(Expense expense) {
        expenses.add(expense);
        String ret = "Got it. I've added this expense: \n" + expense + "\n Now you have "
                + expenses.size() + " expenses in your list in your list";
        return ret;
    }

    /**
     * Prints every item in the expenses Arraylist
     * to the console.
     *
     * @return A String with all items from expenses.
     */
    public String showList() {
        String ret = "Here are the expenses in your list: \n";
        for (int i = 0; i < expenses.size(); i++) {
            int counter = i + 1;
            ret = ret + counter + ". " + expenses.get(i) + "\n";
        }
        return ret;
    }

    /**
     * Deletes an expense from the list.
     *
     * @param n the id of the expense to be deleted.
     * @return A String showing deleted expense.
     */
    public String deleteExpense(int n) {
        Expense deleted = this.expenses.remove(n - 1);
        String ret = "The following expense has been deleted:\n" + deleted;
        return ret;
    }

    /**
     * Returns number of tasks.
     *
     * @return An int showing number of tasks.
     */
    public int numEx() {
        return expenses.size();
    }
}
