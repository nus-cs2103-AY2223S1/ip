/**
 * Abstract class that represents a task.
 *
 * @author WR3nd3
 */

abstract public class Task {
    private  String modifier = "[?]";
    private String description;
    private String addOn = "nya!";
    private Boolean isCompleted;
    private String notDoneSymbol = "[Zzzzzzz]";
    private String doneSymbol    = "[=^._.^=]";

    /**
     * Constructor of the Task object to be called by its subclasses.
     *
     * @param description String representing the details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
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
