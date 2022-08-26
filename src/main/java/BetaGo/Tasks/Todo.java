package BetaGo.Tasks;

/**
 * Todo class is a type of task where users can store their task description
 * and additional date/time information.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation for Todo task.
     *
     * @return String representation for Todo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getTaskDescription();
    }

    /**
     * Returns corresponding string representation of the todo task that is saved in the data file.
     *
     * @return String representation for Todo task to be saved in data file.
     */
    @Override
    public String saveTask() {
        String icon;
        if (this.getStatusIcon() == "X") {
            icon = "1";
        } else {
            icon = "0";
        }
        return "T , " + icon + " , " + this.description + "\n";
    }
}
