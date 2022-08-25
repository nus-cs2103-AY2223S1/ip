package duke.command;

import duke.DukeException;
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
     * @throws DukeException Duke Exception.
     */
    @Override
    public void run(TaskList taskList) throws DukeException {
        Ui.bye();
        System.exit(0);
    }
}
