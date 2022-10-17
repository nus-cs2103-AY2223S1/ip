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
        storage.saveToFile(tasks);
        javafx.application.Platform.exit();
        return FAREWELL_MESSAGE;
    }
}
