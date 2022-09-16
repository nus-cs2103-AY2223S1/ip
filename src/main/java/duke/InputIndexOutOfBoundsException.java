package duke;

/**
 * Represents a Duke exception when the user inputs a task number that exceeds the amount of tasks present.
 */
public class InputIndexOutOfBoundsException extends DukeException {
    InputIndexOutOfBoundsException(String index) {
        super(index);
    }
}
