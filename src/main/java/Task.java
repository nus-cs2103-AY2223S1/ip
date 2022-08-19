/**
 * Abstract class that represents a task.
 *
 * @author WR3nd3
 */

abstract public class Task {
    private  String modifier = "[?]";
    private String description;
    private String addOn = "nya!";
    private Boolean completed;
    private String notDoneSymbol = "[Zzzzzzz]";
    private String doneSymbol    = "[=^._.^=]";

    /**
     * Constructor of the Task object to be called by its subclasses.
     *
     * @param description String representing the details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Marks this task as complete.
     */
    public void markAsDone() {
        this.completed = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markAsNotDone() {
        this.completed = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string consisting of the task completion status and description.
     */
    @Override
    public String toString() {
        String symbol;
        if (this.completed) {
            symbol = doneSymbol;
        } else {
            symbol = notDoneSymbol;
        }
        return symbol + " " + this.description + " " + addOn;
    }
}
