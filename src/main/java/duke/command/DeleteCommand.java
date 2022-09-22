package duke.command;

import java.util.ArrayList;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the command to delete a task from the task list.
 *
 * @author Tan Jun Wei
 */
public class DeleteCommand extends Command {
    /** Index of task to be deleted by this command */
    private int index;

    /** String which task that contain will be deleted */
    private String termToDeleteBy;

    /**
     * Constructs a new DeleteCommand with the given index.
     *
     * @param index Index of task to be deleted by this command.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Constructs a new DeleteCommand with the term to delete by.
     *
     * @param termToDeleteBy String that will be matched to task descriptions to be deleted.
     */
    public DeleteCommand(String termToDeleteBy) {
        this.termToDeleteBy = termToDeleteBy;
    }

    /**
     * Deletes the task at the given index from the task list.
     *
     * @param tasks The task list to be operated on.
     * @param ui The user interface to be used.
     * @param storage The storage to be used.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (termToDeleteBy == null) {
            Task task = tasks.deleteItem(index);
            ui.showOutput("OK, I've deleted the following task:\n  " + task + "\n");
        } else {
            ArrayList<Task> deletedTasks = tasks.deleteItem(termToDeleteBy);
            ui.showOutput("OK, I've deleted the following tasks:\n"
                    + deletedTasks.stream()
                    .map(task -> task.toString() + "\n")
                    .reduce("", String::concat));
        }
        storage.saveAllTasks(tasks);
        ui.showOutput("Now you have " + tasks.size() + " tasks in the list.");
    }
}
