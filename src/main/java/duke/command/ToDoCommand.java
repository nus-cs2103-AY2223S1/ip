package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The ToDoCommand class represents the todo command to add a new todo to the task list.
 */
public class ToDoCommand extends Command {
    private final ToDo todo;

    /**
     * Initializes a new instance of ToDo.
     *
     * @param todo The todo to be added to the task list.
     */
    public ToDoCommand(ToDo todo) {
        super(false);
        this.todo = todo;

    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(todo);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getAddTaskResponse(todo.toString(), taskList.getNumOfTask());
    }
}
