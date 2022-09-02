package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

/**
 * Adds a person to the address book.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static final String MESSAGE_SUCCESS = "Yo, I managed to add this task: \n";

    private Todo todo;

    public TodoCommand(String description) {
        super();
        todo = new Todo(description);
    }

    public TodoCommand(String description, boolean isMarked) {
        super();
        todo = new Todo(description);
        todo.markAsDone();
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(todo);
        ui.showMessage(MESSAGE_SUCCESS + todo + " " + tasks.showNumberOfTasks());
    }
}