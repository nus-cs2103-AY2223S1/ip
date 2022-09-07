package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a Task as completed.
 * Inherits from Command abstract class.
 */
public class MarkCommand extends Command {

    private final int num;

    /**
     * Creates the MarkCommand.
     *
     * @param num The index of Task in TaskList to be marked as completed.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the command given.
     * Task will be updated to completed, data will be updated and saved, message will be printed to user.
     *
     * @param tasks The list that contains all the Tasks on the program.
     * @param ui Deals with the interaction with user.
     * @param storage Deals with the loading and updating of file.
     * @return The String response of Duke after running command.
     * @throws IOException If there is an error when updating the file.
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("Task list is currently empty.");
        }
        assert this.num >= 0 : "Index of Task to be marked has to be at least 1.";
        assert this.num < tasks.getSize() : "Index of Task to be marked has to be less than size of Task list.";
        Task task = tasks.getTask(this.num);
        task.markDone();
        storage.writeFile(tasks);
        return ui.printMark(task);
    }
}
