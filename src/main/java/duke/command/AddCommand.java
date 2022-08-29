package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An extension of the Command class, AddCommand, used to add tasks to taskList.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for AddCommand class
     *
     * @param task the task to be added to constructor
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns false to indicate this command does not cause Duke to exit
     *
     * @return the boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds tasks to taskList, saves the taskList to Duke.txt file, and displays Ui to show update to taskList.
     *
     * @param taskList tasklist that tasks are added to
     * @param ui ui that displays results of user commands
     * @param storage storage that saves the taskList
     * @return string that contains ui message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        storage.saveFile(taskList);
        return ui.showTaskAdded(taskList);
    }
}
