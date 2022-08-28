package duke.command;

import duke.exception.DukeException;

/**
 * Class to encapsulate a unrecognized command.
 */
public class UnknownCommand extends DukeException {

    /**
     * Constructor for UnknownCommand.
     */
    public UnknownCommand() {
        super();
    }

    @Override
    public String toString() {
        return "I'm sorry, I don't understand what you mean.";
    }
}

