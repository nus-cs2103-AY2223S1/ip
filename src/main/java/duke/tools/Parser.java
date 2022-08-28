package duke.tools;

import duke.exception.TaskNotFoundException;

/**
 * Converter of user inputs to commands
 */
abstract public class Parser {
    private String[] keywords;

    /**
     * Returns constant representing user input.
     * @return Constant representing user input.
     * @throws TaskNotFoundException When the user input is not recognised.
     */
    abstract public Enum getCommand() throws TaskNotFoundException;
}
