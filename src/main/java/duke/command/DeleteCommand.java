package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to delete a Task from the TaskList.
 * Inherits from Command abstract class.
 */
public class DeleteCommand extends Command {

    private final int num;

    /**
     * Constructor for DeleteCommand.
     * @param num The index of Task in TaskList to be deleted.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command given.
     * Task will be deleted from TaskList, data will be updated and saved, message will be printed to user.
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     * @throws IOException If there is an error when updating the file.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(this.num);
        tasks.deleteTask(this.num);
        ui.printDeleteTask(task, tasks);
        storage.writeFile(tasks);
    }
}
