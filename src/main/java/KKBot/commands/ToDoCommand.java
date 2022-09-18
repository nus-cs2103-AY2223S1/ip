package KKBot.commands;

import KKBot.storage.Storage;
import KKBot.storage.exceptions.StorageException;
import KKBot.tasks.ToDo;
import KKBot.tasklist.TaskList;
import KKBot.ui.Ui;

/**
 * Class representing the command for KKBot when user inputs a todo task
 *
 * @author AkkFiros
 */

public class ToDoCommand extends Command {
    public static final String KEYWORD = "todo";
    private String description;

    /**
     * Constructor for a ToDoCommand
     * @param description the description of the user-input task
     */
    public ToDoCommand(String description) {
        super();
        this.description = description;
    }

    /**
     * Returns a set of actions done by KKBot when user inputs "todo".
     * @param tasks the list of tasks stored by KKBot
     * @param ui the ui object that governs what response is returned to the user
     * @param storage the storage object to save tasks to hard drive
     * @return the related messages after a todo task is added to KKBot
     * @throws StorageException if there is an error reading from/writing to hard drive
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        ToDo task = new ToDo(description);
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showTaskAddition(task)
                + ui.showNumberOfTasks(tasks.getNumberOfTasks());
    }
}
