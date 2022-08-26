package duke.exception;

/**
 * Represents an error during parsing of input from the user.
 * This error will occur when the user tries to access a number that is out of the range of the task list.
 */
public class MarkException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "please input a valid task number";
    }
}
