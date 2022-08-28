package duke.tools;

import duke.exception.TaskNotFoundException;

/**
 * Converter of user inputs to commands
 */
public abstract class Parser {
    private String[] keywords;

<<<<<<< HEAD
    public abstract Enum getCommand() throws TaskNotFoundException;
=======
    /**
     * Returns constant representing user input.
     * @return Constant representing user input.
     * @throws TaskNotFoundException When the user input is not recognised.
     */
    abstract public Enum getCommand() throws TaskNotFoundException;
>>>>>>> 4dd3ea8 (add JavaDocs to some classes for A-JavaDocs)
}
