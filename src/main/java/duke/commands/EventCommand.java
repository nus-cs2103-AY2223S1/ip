package duke.commands;

import java.util.Objects;

import duke.DukeException;
import duke.Message;
import duke.task.Event;
import duke.task.TaskList;


/**
 * Command to create new Event.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this event:\n"
            + "\t%1$s\nNow you have %2$d tasks in your list";
    public static final String MESSAGE_FAILURE = "Unable to add event.";

    private final Event toAdd;

    public EventCommand(String description, String eventTime) {
        this.toAdd = new Event(description, eventTime);
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        if (tasks.addTask(this.toAdd)) {
            return new Message(String.format(MESSAGE_SUCCESS, this.toAdd, tasks.size()),
                    false, Message.User.DUKE);
        } else {
            throw new DukeException(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventCommand)) {
            return false;
        }
        EventCommand that = (EventCommand) o;
        return Objects.equals(toAdd, that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
