package dukepro.expenses;

import java.util.ArrayList;

public class ExpenseCalculator {
    public static int sumArrayList(ArrayList<? extends Expense> expenses) {
        int sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum = sum + expenses.get(i).spent();
        }
        return sum;
    }
}
