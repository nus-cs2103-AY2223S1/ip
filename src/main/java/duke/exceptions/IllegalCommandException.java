package duke.exceptions;


/**
 * Is thrown when the user inputs an unknown command.
 */
public class IllegalCommandException extends IllegalArgumentException {

    /**
     * Checks whether obj is also an IllegalCommandException.
     * @param obj Object to be compared.
     * @return True if the object is an IllegalCommandException, false otherwise.
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
        return "OOPS!!! I'm sorry, but I don't know what that means.";
    }
}
