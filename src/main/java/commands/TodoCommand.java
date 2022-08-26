package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import task.TaskType;
import task.Todo;

/**
 * Creates a new Todo.
 */
public class TodoCommand extends Command {
    private final String[] inputStrings;

    public TodoCommand(String[] inputStrings) {
        this.inputStrings = inputStrings;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputStrings.length == 1) {
            throw new EmptyDescriptionException(TaskType.T);
        }

        Todo todo = new Todo(inputStrings[1], false);
        tasks.addTask(todo);

        ui.showAddTask(todo, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
