package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Todo;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class TodoCommand implements Command {

    private Todo todo;

    public TodoCommand(String desc) {
        this.todo = new Todo(desc);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(this.todo);
            storage.appendToFile(todo);
            ui.addTask(this.todo);
            ui.showTaskListCapacity(taskList);
        } catch (DukeException e) {
            ui.handleException(e);
        }
    }
}
