package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to add a new Todo object
 */
public class AddTodoCommand extends Command {
    /* Description of the task. */
    private String description;

    public AddTodoCommand(String description) {
        assert description != null: "Description cannot be null";
        this.description = description;
    }

    /**
     * Returns a task added status string after adding task to list.
     *
     * @param tasks TaskList object to add the task to.
     * @param storage Storage object to manage save file.
     * @return Task added status string.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Todo t = new Todo(description);
        tasks.add(t);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.ADDED, t);
    }
}
