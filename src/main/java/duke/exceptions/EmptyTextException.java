package duke.exceptions;

/**
 * Is thrown when user does not input text for description of a Todo task.
 */
public class EmptyTextException extends IllegalArgumentException {

    /**
     * Checks whether obj is also an EmptyTextException.
     * @param obj Object to be compared.
     * @return True if the object is an EmptyTextException, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }

    /**
     * Outputs the error to the user.
     * @return Error text.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
