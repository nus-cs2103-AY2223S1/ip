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
            ui.sayAddTask(this.todo);
            ui.sayTaskListSize(taskList);
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TodoCommand) {
            TodoCommand that = (TodoCommand) o;
            if (this.todo.equals(that.todo)) {
                return true;
            }
        }
        return false;
    }
}
