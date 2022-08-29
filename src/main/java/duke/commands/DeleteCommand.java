package duke.commands;

import java.util.Objects;

import duke.DukeException;
import duke.Message;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE_SUCCESS = "Noted. I've removed this task:\n"
            + "\t%s\nNow you have %d tasks in your list";
    public static final String MESSAGE_DELETE_ERROR = "Failed to delete task %d";

    private final int indexToDelete;

    public DeleteCommand(int task) {
        this.indexToDelete = task - 1;
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        try {
            Task task = tasks.removeTask(this.indexToDelete);
            return new Message(String.format(MESSAGE_DELETE_SUCCESS, task, tasks.size()),
                    false, Message.User.DUKE);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_DELETE_ERROR, indexToDelete + 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return indexToDelete == that.indexToDelete;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexToDelete);
    }
}
