package duke.command;

import duke.main.Ui;
import duke.main.TaskList;
import duke.main.Storage;
import duke.task.Todo;
import duke.task.Task;

/**
 * Represents a command that is used to add a Todo task into a tasklist, save the Todo
 * and print out the Todo that was added.
 */
public class AddTodoCommand extends Command {
    private String taskName;

    /**
     * Constructor for the AddEventCommand.
     *
     * @param taskName
     */
    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Execute method that is used to add a Todo task to a tasklist, save the Todo
     * and print out the Todo that was added through tasklist, ui and storage.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task todo = new Todo(taskName);
        taskList.addTasks(todo);
        storage.saveTasks(taskList);
        ui.repeater(todo + " added!");
    }

}
