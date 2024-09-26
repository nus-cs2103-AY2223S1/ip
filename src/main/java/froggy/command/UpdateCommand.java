package froggy.command;

import froggy.task.Task;
import froggy.storage.Storage;
import froggy.task.TaskList;
import froggy.ui.Ui;

/**
 * A class which extends from the Command abstract class.
 * A UpdatedCommand object can be used to update a given task based on the index, typeOfUpdate and updatedValue.
 */
public class UpdateCommand extends Command {
    /* The index of the task based on the output of ListCommand.execute(...). */
    protected int indexOfTask;
    protected String typeOfUpdate;
    protected String updatedValue;

    /**
     * Creates a DeleteCommand object.
     *
     * @param indexOfTask The task's index based on the output of the ListCommand.execute(...).
     */
    public UpdateCommand(int indexOfTask, String typeOfUpdate, String updatedValue) {
        super(false);
        this.indexOfTask = indexOfTask;
        this.typeOfUpdate = typeOfUpdate;
        this.updatedValue = updatedValue;
    }

    /**
     * Updates a task from the TaskList object.
     * The output is then printed through ui.updateTask method.
     *
     * @param tasks The TaskList object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.updateTask(indexOfTask, typeOfUpdate, updatedValue);
        storage.writeToFile(tasks);
        String messageToUser = ui.updateTask(task);
        return messageToUser;
    }
}
