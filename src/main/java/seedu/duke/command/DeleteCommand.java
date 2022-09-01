package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * A class which extends from the Command abstract class.
 * A DeleteCommand object can be used to delete a given task based on the index.
 */
public class DeleteCommand extends Command {
    /* The index of the task based on the output of ListCommand.execute(...). */
    protected int indexOfTask;

    /**
     * Creates a DeleteCommand object.
     *
     * @param indexOfTask The task's index based on the output of the ListCommand.execute(...).
     */
    public DeleteCommand(int indexOfTask) {
        super(false);
        this.indexOfTask = indexOfTask;
    }

    /**
     * Deletes a task from the TaskList object.
     * The output is then printed through ui.deleteTask method.
     *
     * @param tasks The tasks object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(indexOfTask);
        storage.writeToFile(tasks);
        return ui.deleteTask(task) + ui.displayNumberOfTasks(tasks.getNumberOfTasks());
    }
}
