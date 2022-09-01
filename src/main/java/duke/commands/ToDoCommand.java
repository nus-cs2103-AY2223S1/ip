package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    public String input;

    public ToDoCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Hold up! Description cannot be empty!");
        }
        ToDo newTodo = new ToDo(input);
        taskList.addTask(newTodo);
        ui.showAddTask(newTodo, taskList);
    }

    public boolean isExit() {
        return false;
    }
}
