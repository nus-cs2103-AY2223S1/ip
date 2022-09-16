package duke.task;

import java.util.Objects;

/**
 * Extends from Task as it is a more specific/well-defined task,
 * it is differentiated by having no deadline/due dates in user-input.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Todo extends Task {

    /**
     * Constructor method for an instance of Todo and it also utilises
     * the constructor of it's parent class.
     *
     * @param description String that contains the details about the task
     * @param isDone Boolean to keep track if the task has been marked before
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Provides a String representation of the Todo task.
     *
     * @return String representation of a task without deadline/due dates.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Overridden equals method to check if two Todo Task are the same.
     *
     * @param o Object to be compared against an instance of Todo
     * @return true if the Object is an instance of Todo and both have the same task
     *     description/toString representation
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof Todo) {
            Todo t = (Todo) o;
            return Objects.equals(t.toString(), this.toString());
        }

        return false;
    }
}
