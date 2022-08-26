package duke.exception;

/**
 * Represents an error during parsing of input from the user.
 * This error will occur when user input is not in the following list:
 * bye, list, mark, unmark, todo, deadline, event, find
 */
public class InputException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "Invalid input, please use only:\ntodo, deadline, event with a task";
    }
}
