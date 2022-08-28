package Duke.commands;

import Duke.exceptions.DukeException;
import Duke.storage.Storage;
import Duke.tasks.TaskList;
import Duke.ui.UI;
import Duke.tasks.Todo;

public class AddTodoCommand extends Command {

    private final String input;

    public AddTodoCommand(String input) throws DukeException {
        if (!checkValid(input))
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String taskDesc = input.substring(5);
        Todo todo = new Todo(taskDesc);
        taskList.addTask(todo);
        ui.addTaskMessage(todo, taskList.size());
        storage.store(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
