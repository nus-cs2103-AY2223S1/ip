/**
 * Encapsulates a todo, a task which has to be completed without any time constraints.
 *
 * @author Conrad
 */
public class Todo extends Task {

    /**
     * Constructor for creating a todo.
     *
     * @param taskDescription A description of the todo.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String getTextRepresentation() {
        return "T|" + (this.isCompleted() ? "1|" : "0|") + this.getTaskDescription() + "\n";
    }

    /**
     * Return a string representation of a todo.
     *
     * @return The string representation of a todo.
     */
    @Override
    public String toString() {
        String taskStatusIndicator = "[T]" + (this.isCompleted() ? "[X] " : "[ ] ");
        return taskStatusIndicator + this.getTaskDescription();
    }
}
