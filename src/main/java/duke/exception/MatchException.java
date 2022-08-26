package duke.exception;

/**
 * Represents an error during parsing of input from the user.
 * This error will occur when Duke does not find any task with a matching string to the input.
 */
public class MatchException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "No matching tasks found!";
    }
}
