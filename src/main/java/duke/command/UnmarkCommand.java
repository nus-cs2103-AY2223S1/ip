package duke.command;

import duke.*;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * duke.command.Command to mark duke.task.Task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor to populate the taskIndex field.
     * @param taskIndex Index of the duke.task.Task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Run the given command as an duke.command.UnmarkCommand.
     * @param taskList duke.main.TaskList containing the list of tasks.
     * @param ui duke.main.Ui dealing interaction with user.
     * @param storage duke.main.Storage dealing with loading tasks from the save file and saving task in the save file.
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task taskToUnmark = taskList.getTasks().get(this.taskIndex - 1);
        taskToUnmark.markAsNotDone();
        ui.printMarkNotDone(taskToUnmark);
        storage.update(taskList);
    }
}
