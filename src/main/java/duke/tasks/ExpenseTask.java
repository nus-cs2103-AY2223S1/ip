package duke.tasks;

/**
 * Task that represents Expenses.
 */
public class ExpenseTask extends Task {
    private int expenseAmount;

    /**
     * Default construtor for producing the expense Task
     * @param expenseName Name of the expense.
     * @param expenseAmount Amount of the expense.
     */
    public ExpenseTask(String expenseName, int expenseAmount) {
        super(expenseName);
        this.expenseAmount = expenseAmount;
    }

    public int getAmount() {
        return this.expenseAmount;
    }

    /**
     * {@inheritDoc}
     * @return String representation of the Events Task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.EX + "]" + "[" + this.getStatusIcon() + "] " + this.getName()
                + " (amt: " + this.getAmount() + ")";
    }

    /**
     * Return the String representation of the object in CSV.
     * The following attributes are saved.
     * Type of task - DL,EV,TD,EX.
     * Marked status - X," ".
     * Name.
     * Amount - Integer.
     *
     * @return String representation of Todo Task in CSV.
     */
    @Override
    public String toCsv() {
        return TaskType.EX + "," + this.getStatusIcon() + "," + this.getName() + ","
                + this.getAmount();
    }
}
