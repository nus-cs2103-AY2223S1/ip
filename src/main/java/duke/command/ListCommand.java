package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command 'list'.
 */
public class ListCommand extends Command {

    /**
     * Runs the command 'list'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @throws DukeException Duke Exception.
     */
    @Override
    public void run(TaskList taskList) throws DukeException {
        String toDisplay = "Your current list is:\n  ";
        for (int i = 0; i < taskList.getSize() - 1; i++) {
            toDisplay += (i + 1) + ": " + taskList.getTask(i) + "\n  ";
        }
        toDisplay += (taskList.getSize()) + ": " + taskList.getTask(taskList.getSize() - 1);
        Ui.formatMessage(toDisplay);
    }
}
