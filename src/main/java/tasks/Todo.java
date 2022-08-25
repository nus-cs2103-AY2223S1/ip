package tasks;

/**
 * Manager class for todo tasks. Handles the
 * parsing and formatting of the task itself.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     * @param taskDescription Description of the task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Format of parsing:
     * Type of task # status of task # description # timing
     * @return String that is in the parsing format.
     */
    @Override
    public String parseToFile() {
        return String.format("T # %s # %s # ", super.getStatusIcon(), super.getTaskDescription());
    }

    /**
     * Returns a formatted todo task to the user.
     * @return Formatted todo task to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
