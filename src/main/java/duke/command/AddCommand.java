package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to add a Task to the TaskList.
 * Inherits from Command abstract class.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Creates an AddCommand.
     *
     * @param task The Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command given.
     * Task will be added to TaskList, data will be updated and saved, message will be printed to user.
     *
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     * @return The String response of Duke after running command.
     * @throws IOException If there is an error when updating the file.
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(this.task);
        storage.writeFile(tasks);
        return ui.printAddTasks(this.task, tasks);
    }
}
