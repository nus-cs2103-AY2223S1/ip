package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Todo;

/**
 * Adds a todo task when command is called.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Adds a todo based on command received
     *
     * @param description description of todo
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.outputMessage("Got it. I've added this task:\n"
                + todo
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.");
        storage.save(tasks);
    }
}
