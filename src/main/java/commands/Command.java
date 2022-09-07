package commands;

import drivers.Storage;
import drivers.TaskList;
import drivers.Ui;
import exceptions.TumuException;
import tasks.Task;

/**
 * Parent class for executing commands.
 */
public abstract class Command {

    /**
     * Executes the command and gives the appropriate
     * feedback to the user.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     * @param storage Stores and retrieves data from a local .txt file.
     * @throws TumuException Parent exception for the program.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws TumuException;

    /**
     * Add a task to the task list.
     * @param task Specifies the task to be added to the task list.
     * @param tasks TaskList containing all the tasks currently available.
     * @param ui Specifies how the program interacts with the user.
     */
    protected String addTaskType(Task task, Storage storage, TaskList tasks, Ui ui) {
        String output = ui.notifyUser("I've added a task into your list:\n\t\t" + task);
        assert(tasks != null);
        tasks.addTask(task);
        saveUserTasks(storage, tasks, ui);
        return output + ui.notifyUser(String.format("You have %d task(s) in the list.", tasks.getListSize()));
    }

    /**
     * Save the task list into the local txt file.
     * @param storage Stores and retrieves data from a local .txt file.
     * @param tasks TaskList containing all the tasks currently available.
     */
    protected void saveUserTasks(Storage storage, TaskList tasks, Ui ui) {
        storage.saveData(tasks.getTasks(), ui);
    }
}
