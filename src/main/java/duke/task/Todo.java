package duke.task;

import java.util.Objects;

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
     * @param isDone a boolean to keep track if the task has been marked before
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
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

    /**
     * Overridden equals method to check if two Todo Task are the same.
     *
     * @param o an Object to be compared against an instance of Todo
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
