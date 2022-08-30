package duke.commands;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.DukeException;
import duke.Message;
import duke.task.Deadline;
import duke.task.TaskList;


/**
 * Command for creating a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this deadline:\n"
            + "\t%s\nNow you have %d tasks in your list";
    public static final String MESSAGE_FAILURE = "Unable to add deadline.";

    private final Deadline toAdd;

    /**
     * Creates a Deadline Command with description and an event time.
     *
     * @param description description of deadline.
     * @param eventTime date time of deadline.
     */
    public DeadlineCommand(String description, LocalDateTime eventTime) {
        this.toAdd = new Deadline(description, eventTime);
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        if (tasks.addTask(this.toAdd)) {
            return new Message(String.format(MESSAGE_SUCCESS, this.toAdd, tasks.size()), false, Message.User.DUKE);
        } else {
            return new Message(MESSAGE_FAILURE, false, Message.User.DUKE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeadlineCommand)) {
            return false;
        }
        DeadlineCommand that = (DeadlineCommand) o;
        return toAdd.equals(that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
