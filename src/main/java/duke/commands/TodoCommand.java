package duke.commands;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.task.Todo;
import duke.util.Ui;

import java.util.ArrayList;

public class TodoCommand extends Command{

    public TodoCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    protected String addTodo(ArrayList<String> parsedInput) throws DukeException {
        Todo newTodo = new Todo(parsedInput.get(1), false);
        taskList.addTask(newTodo);
        storage.saveTask(taskList);
        return ui.printAddedTask(newTodo, taskList);
    }
}
