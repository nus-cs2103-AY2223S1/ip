package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to update a task's date field.
 * Inherits from Command abstract class.
 */
public class UpdateCommand extends Command {
    private final int num;
    private final LocalDate date;

    /**
     * Creates the DeleteCommand.
     *
     * @param num The index of Task in TaskList to be deleted.
     */
    public UpdateCommand(int num, LocalDate date) {
        this.num = num;
        this.date = date;
    }

    /**
     * Executes the command given.
     * Task will be updated in TaskList, data will be updated and saved, message will be printed to user.
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
            throw new DukeException("Task list is empty. There is nothing to update.");
        }
        assert this.num >= 0 : "Index of Task to be deleted has to be at least 1.";
        assert this.num < tasks.getSize() : "Index of Task to be deleted has to be less than size of Task list.";
        Task task = tasks.getTask(this.num);
        task.updateTaskDate(this.date);
        storage.writeFile(tasks);
        return ui.printUpdateTask(task);
    }
}
