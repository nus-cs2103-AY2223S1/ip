package duke.exceptions;

/**
 * Is thrown when the user inputs command to exit the program.
 */
public class EndProgramException extends Exception {

    /**
     * Checks whether obj is also an EndProgramException.
     * @param obj Object to be compared.
     * @return True if the object is an EndProgramException, false otherwise.
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
        return "Bye. Hope to see you again soon!";
    }
}
