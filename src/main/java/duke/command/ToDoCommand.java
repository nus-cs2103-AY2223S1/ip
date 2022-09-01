package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private final ToDo todo;

    public ToDoCommand(ToDo todo) {
        super(false);
        this.todo = todo;

    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.addTask(todo);
        storage.overwriteFile(taskList.toStorageString());
        return Command.wrapper.getAddTaskResponse(todo.toString(), taskList.getNumOfTask());
    }
}
