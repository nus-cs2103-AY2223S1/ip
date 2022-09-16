package duke.exception;

/**
 * The InvalidTaskNumberException which occurs when
 * a user does not input a valid task number.
 *
 * @author Leong Jia Hao Daniel
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Constructs the InvalidTaskNumberException.
     */
    public InvalidTaskNumberException() {
        super("InvalidTaskNumber exception");
    }

    /**
     * Returns the String for the invalid task number.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return "OOPS!!! Please input a valid task number!";
    }

}
