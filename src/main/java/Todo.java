/**
 * The Todo class extends from Task as it is a more specific/well-defined
 * task, it is differentiated by having no deadline/due dates in user-input.
 */
public class Todo extends Task {

    /**
     * Constructor method for an instance of Todo and it also utilises
     * the constructor of it's parent class (Task)
     *
     * @param description String that contains the details about the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A string method to represent the Todo task.
     *
     * @return a string representation of a task without deadline/due dates.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
