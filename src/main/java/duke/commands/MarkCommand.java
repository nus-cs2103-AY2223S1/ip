package duke.commands;

import java.util.Objects;

import duke.DukeException;
import duke.Message;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark task as completed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n  %s";

    private final int indexToMark;

    public MarkCommand(int task) {
        this.indexToMark = task - 1;
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        Task task = tasks.setCompletion(this.indexToMark, true);
        return new Message(String.format(MESSAGE_SUCCESS, task), false, Message.User.DUKE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }
        MarkCommand that = (MarkCommand) o;
        return indexToMark == that.indexToMark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexToMark);
    }
}
