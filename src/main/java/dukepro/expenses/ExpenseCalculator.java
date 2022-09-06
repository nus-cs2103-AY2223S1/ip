package dukepro.expenses;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenseCalculator {
    public static int sumArrayList(ArrayList<? extends Expense> expenses) {
        int sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            sum = sum + expenses.get(i).spent();
        }
        return sum;
    }

    public static int spentDay(ArrayList<? extends Expense> expenses, LocalDate localDate) {
        int sum = 0;
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).compareDate(localDate)) {
                sum = sum + expenses.get(i).spent();
            }
        }
        return sum;
    }
}
