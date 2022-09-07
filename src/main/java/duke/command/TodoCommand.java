package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {
    private String task;

    /**
     * Creates a new TodoCommand.
     *
     * @param task
     */
    public TodoCommand(String task) {
        super();
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Task(task));
        return ui.printAddTaskSuccessfully(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof TodoCommand == false) {
            return false;
        }
        TodoCommand that = (TodoCommand) o;
        return task.equals(that.task);
    }
}
