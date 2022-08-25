package duke.command;

import duke.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * duke.command.Command to delete duke.task.Task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor to populate the taskIndex field.
     * @param taskIndex Index of the duke.task.Task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Run the given command as an duke.command.MarkCommand.
     * @param taskList duke.main.TaskList containing the list of tasks.
     * @param ui duke.main.Ui dealing interaction with user.
     * @param storage duke.main.Storage dealing with loading tasks from the save file and saving task in the save file.
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        ui.printDelete(this.taskIndex, taskList);
        taskList.removeTask(this.taskIndex - 1);
        storage.update(taskList);
    }
}
