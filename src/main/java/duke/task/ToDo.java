package duke.task;

import java.time.LocalDate;

/**
 * Represents a task to be conducted which is one type of task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance with a description.
     *
     * @param description Description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Checks if a todo has a matching date.
     *
     * @param localDate Given date.
     * @return False.
     */
    @Override
    public boolean matchDate(LocalDate localDate) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns if a given object is equal to a ToDo instance.
     *
     * @param object Given object.
     * @return True if the two are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof ToDo) {
            return super.equals(object);
        } else {
            return false;
        }
    }

}
