
package duke;

public class Todo extends Task{
    /**
     * initializes a todo object.
     * @param description string description of todo event.
     */
    public Todo(String description) {
        super(description);

    }

    /**
     * Returns string representation of the task
     * @return String representation of this task
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "]"  + super.toString();
    }
}
