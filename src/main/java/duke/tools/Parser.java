package duke.tools;

import duke.exception.TaskNotFoundException;

/**
 * Converter of user inputs to commands
 */
public abstract class Parser {
    private String[] keywords;

    public abstract Enum getCommand() throws TaskNotFoundException;
}
