package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command that adds the task to the list.
 *
 * @author Bryan Ng Zi Hao
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added to the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task as provided by the user.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Duke's reply.
     * @throws DukeException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.loadTask(task);
        String output = ui.formatMessage("Got it. I've added this task:\n" + task + "\n");
        if (taskList.size() == 1) {
            return output + ui.formatMessage(String.format("Now you have %d task in the list.", taskList.size()));
        } else {
            return output + ui.formatMessage(String.format("Now you have %d tasks in the list.", taskList.size()));
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
