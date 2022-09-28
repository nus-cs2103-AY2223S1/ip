package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.task.Todo;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents a class creates a Todo Task and adds it to the list
 *
 */
public class TodoCommand extends Command {

    public TodoCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    /**
     * Creates a new Todo Task and adds it to the list
     *
     * @param parsedInput
     * @return Response to be displayed
     * @throws DukeException
     */
    protected String addTodo(ArrayList<String> parsedInput) throws DukeException {
        Todo newTodo = new Todo(parsedInput.get(1), false);
        assert newTodo.isTodo() : "Task should be a Todo!";
        taskList.addTask(newTodo);
        storage.saveTask(taskList);
        return ui.printAddedTask(newTodo, taskList);
    }
}
