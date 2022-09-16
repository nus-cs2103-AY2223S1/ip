package duke.command;

import duke.duke.Duke;
import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;

/** Represents the command to end the Duke program. */
public class ExitCommand extends Command {

    /** Represents a ExitCommand object */
    public ExitCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        storage.saveTaskList(taskList);
        return Duke.EXIT_MESSAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
