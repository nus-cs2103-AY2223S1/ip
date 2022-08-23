package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Objects;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Noted. I've removed this task:\n  %s\nNow you have %d tasks in your list";

    private final int indexToDelete;

    public DeleteCommand(int task) {
        this.indexToDelete = task - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.removeTask(indexToDelete);
        ui.displayText(MESSAGE_DELETE_SUCCESS, task, tasks.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeleteCommand)) return false;
        DeleteCommand that = (DeleteCommand) o;
        return indexToDelete == that.indexToDelete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexToDelete);
    }
}
