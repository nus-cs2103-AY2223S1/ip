package duke.expenses;

import java.util.ArrayList;

public class ExpenseList {
    protected ArrayList<Expense> expenseList;
    protected int numExpenses;

    public ExpenseList() {
        this.expenseList = new ArrayList<Expense>();
        this.numExpenses = 0;
    }

    public ExpenseList(ArrayList<Expense> expenseList) {
        this.expenseList = expenseList;
        this.numExpenses = expenseList.size();
    }

    public Expense getExpense(int position) {
        return this.expenseList.get(position - 1);
    }

    public int getListSize() {
        return numExpenses;
    }

    public ArrayList<Expense> getList() {
        return this.expenseList;
    }

    public String addExpense(Expense expense) {
        expenseList.add(expense);
        numExpenses += 1;
        String output = "Got it. I've added this expense:\n";
        output += expense.toString() + "\n";
        return output;
    }

    public String deleteExpense(int position) {
        if (numExpenses == 0) {
            return "Expense list empty. Yay you haven't spent money!\n";
        }
        else if (position > numExpenses) {
            return "No expense at position" + Integer.toString(position) + "\n";
        } else {
            Expense expense = expenseList.remove(position - 1);
            numExpenses -= 1;
            String output = "Noted, I have removed this expense:\n";
            output += expense.toString() + "\n";
            return output;
        }
    }

    public String find(String keyword) {
        String output = "Here are the expenses that you might be looking for: \n";
        int matchesFound = 0;
        for (int i = 0; i < numExpenses; i++) {
            Expense expense = expenseList.get(i);
            String activity = expense.getActivity();
            if (activity.contains(keyword)) {
                output += "\n" + Integer.toString(i + 1) + "." + expense.toString();
                matchesFound += 1;
            }
        }
        if (matchesFound > 0) {
            return output;
        } else {
            return "No matches found!";
        }
    }

    public String printList() {
        String output = "Here are your expenses:\n";
        float totalExpenses = 0;
        for (int i = 0; i < numExpenses; i++) {
            Expense expense = expenseList.get(i);
            output += Integer.toString(i + 1) + ". " + expense.toString() + "\n";
            totalExpenses += expense.getCostFloat();
        }
        output += "\nCurrent total expenses: $" + Float.toString(totalExpenses); 
        return output;
    }
}
