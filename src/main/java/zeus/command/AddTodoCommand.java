package zeus.command;

import zeus.exception.ZeusException;
import zeus.main.Storage;
import zeus.main.TaskList;
import zeus.main.Ui;
import zeus.task.Todo;

/**
 * Handles adding Todo to tasks.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructor of AddTodoCommand class.
     *
     * @param description String that describes Todo
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command of adding the Todo.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
        taskList.saveCurrentTaskListVersion();

        Todo todo = new Todo(description);
        taskList.addTask(todo);
        ui.printAddTask(todo, taskList.getSize());
        storage.saveTasksToDisk(taskList.getTaskList());
    }
}
