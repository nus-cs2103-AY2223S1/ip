package duke.expenses;

public class Expense {
    protected String activity;
    protected float expense;

    public Expense(String activity, float expense) {
        this.activity = activity;
        this.expense = expense;
    }

    public Expense(String activity, String expense) {
        this.activity = activity;
        this.expense = Float.parseFloat(expense);
    }

    public String getActivity() {
        return this.activity;
    }

    public String getCostString() {
        return Float.toString(this.expense);
    }

    public float getCostFloat() {
        return this.expense;
    }

    @Override
    public String toString() {
        return "You spent " + "$" + Float.toString(expense) + " on " + this.activity;
    }
}
