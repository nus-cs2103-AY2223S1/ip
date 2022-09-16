package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command that is used to add a Todo task into a tasklist, save the Todo
 * and print out the Todo that was added.
 */
public class AddTodoCommand extends Command {
    private String taskName;

    /**
     * Constructor for the AddEventCommand.
     *
     * @param task
     */
    public AddTodoCommand(String task) {
        int taskIndex = 5;
        this.taskName = task.substring(taskIndex);
    }

    /**
     * Returns a string after method is used to add a Todo task to a tasklist,
     * save the Todo and print out the Todo that was added through
     * tasklist, ui and storage.
     *
     * @param taskList
     * @param archiveTaskList
     * @param storage
     * @param archiveStorage
     * @param ui
     */
    @Override
    public String execute(TaskList taskList, TaskList archiveTaskList, Storage storage,
                          Storage archiveStorage, Ui ui) {
        Task todo = new Todo(taskName);
        taskList.addTasks(todo);
        storage.saveTasks(taskList);
        String response = todo + " added";
        return response;
    }

}
