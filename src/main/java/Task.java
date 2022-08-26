/**
 * Abstract class that represents a task.
 *
 * @author WR3nd3
 */
abstract public class Task {

    protected final Task_Id id;
    protected String description;
    protected boolean isCompleted;
    private String addOn = "nya!";
    private String notDoneSymbol = "[Zzzzzzz]";
    private String doneSymbol    = "[=^._.^=]";

    /**
     * Constructor of the Task object to be called by its subclasses.
     *
     * @param id Task_Id detailing the type of the task.
     * @param description String representing the details of the task.
     * @param isCompleted Boolean representing whether the task is completed.
     */
    public Task(Task_Id id, String description, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks this task as complete.
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markAsNotDone() {
        this.isCompleted = false;
    }

    /**
     * Returns summary of the task that can be deciphered by ListLoader.
     *
     * @return String representing summary of task.
     */
    public abstract String summary();

    /**
     * Returns the type of the task.
     *
     * @return Task_Id type of the task.
     */
    public Task_Id getType() {
        return id;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return a string consisting of the task completion status and description.
     */
    @Override
    public String toString() {
        String symbol;
        if (this.isCompleted) {
            symbol = doneSymbol;
        } else {
            symbol = notDoneSymbol;
        }
        return symbol + " " + this.description + " " + addOn;
    }
}
