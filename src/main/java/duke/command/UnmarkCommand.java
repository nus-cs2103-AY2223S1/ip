package duke.command;

import duke.*;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Command to mark Task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor to populate the taskIndex field.
     *
     * @param taskIndex Index of the Task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Run the given command as an UnmarkCommand.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @return String containing the message to user
     * @throws IOException If error is encountered saving onto the save file.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Task taskToUnmark = taskList.getTasks().get(this.taskIndex - 1);
        taskToUnmark.markAsNotDone();
        storage.update(taskList);
        return ui.printMarkNotDone(taskToUnmark);
    }
}
