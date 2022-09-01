package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command 'bye'.
 */
public class ByeCommand extends Command {

    /**
     * Runs the command 'bye'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        Storage.writeStorage(taskList);
        builder.append(Ui.bye());
        System.exit(0);
    }
}
