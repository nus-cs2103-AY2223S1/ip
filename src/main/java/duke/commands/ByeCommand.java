package duke.commands;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.exceptions.DukeException;

/**
 * This class performs termination command.
 */
public class ByeCommand implements Command {
    public static final String COMMAND_WORD = "bye";
    private static final String FAREWELL_MESSAGE = "Bye! Hope to see you again soon!";

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        javafx.application.Platform.exit();
        storage.saveToFile(tasks);
        return FAREWELL_MESSAGE;
    }
}
