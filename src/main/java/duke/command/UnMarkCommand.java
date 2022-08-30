package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task as uncompleted.
 * Inherits from Command abstract class.
 */
public class UnMarkCommand extends Command {

    private final int num;

    /**
     * Creates the unMarkCommand.
     *
     * @param num The index of Task in TaskList to be marked as uncompleted.
     */
    public UnMarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command given.
     * Task will be updated to uncompleted, data will be updated and saved, message will be printed to user.
     *
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     * @return The String response of Duke after running command.
     * @throws IOException If there is an error when updating the file.
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.getTask(this.num);
        task.unMarkDone();
        storage.writeFile(tasks);
        return ui.printUnMark(task);
    }
}
