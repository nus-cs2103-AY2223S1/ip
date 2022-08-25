package duke.command;

import duke.*;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * duke.command.Command to add duke.task.Task to duke.main.TaskList.
 */
public class AddCommand extends Command {
    private final Task taskToAdd;

    /**
     * Constructor that populate the taskToAdd field
     * @param taskToAdd duke.task.Task to be added.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Run the given command as an duke.command.AddCommand to add duke.task.Task to duke.main.TaskList.
     * @param taskList duke.main.TaskList containing the list of tasks.
     * @param ui duke.main.Ui dealing interaction with user.
     * @param storage duke.main.Storage dealing with loading tasks from the save file and saving task in the save file.
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        taskList.addTask(this.taskToAdd);
        storage.update(taskList);
        ui.printAdd(this.taskToAdd, taskList);
    }
}
