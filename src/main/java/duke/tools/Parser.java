package duke.tools;

import duke.exception.TaskNotFoundException;

/**
 * Converter of user inputs to commands
 */
abstract public class Parser {
    private String[] keywords;

    abstract public Enum getCommand() throws TaskNotFoundException;
}
