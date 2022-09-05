package dukepro.expenses;

import java.util.ArrayList;

public class ExpenseManager {
    private ArrayList<Expense> expenses = new ArrayList<>();

    public String addExpense(Expense expense) {
        expenses.add(expense);
        String ret = "Got it. I've added this expense: \n" + expense + "\n Now you have "
                + expenses.size() + " expenses in your list in your list";
        return ret;
    }
}
