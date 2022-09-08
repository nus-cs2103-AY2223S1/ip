package duke.command;

import duke.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Command to delete Task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor to populate the taskIndex field.
     *
     * @param taskIndex Index of the Task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Run the given command as a MarkCommand.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @return String containing the message to user
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        String msg = ui.printDelete(this.taskIndex, taskList);
        taskList.removeTask(this.taskIndex);
        storage.update(taskList);
        return msg;
    }
}
