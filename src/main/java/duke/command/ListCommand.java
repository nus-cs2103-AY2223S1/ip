package duke.command;

import duke.DukeException;
import duke.Response;
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
     * @param builder
     * @throws DukeException Duke Exception.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        if (taskList.getSize() == 0) {
            builder.append("Your list is empty!");
        } else {
            String toDisplay = "Your current list is:\n  ";
            for (int i = 0; i < taskList.getSize() - 1; i++) {
                toDisplay += (i + 1) + ": " + taskList.getTask(i) + "\n  ";
            }
            toDisplay += (taskList.getSize()) + ": " + taskList.getTask(taskList.getSize() - 1);
            builder.append(toDisplay);
        }
    }
}
