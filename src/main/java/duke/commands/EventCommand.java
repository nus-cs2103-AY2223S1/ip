package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Objects;

/**
 * Create new ToDo.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this event:\n  %1$s\nNow you have %2$d tasks in your list";
    public static final String MESSAGE_FAILURE = "Unable to add event.";

    private final Event toAdd;

    public EventCommand(String description, String eventTime) {
        this.toAdd = new Event(description, eventTime);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (tasks.addTask(this.toAdd)) {
            ui.displayText(MESSAGE_SUCCESS, this.toAdd, tasks.size());
        } else {
            ui.displayText(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventCommand)) return false;
        EventCommand that = (EventCommand) o;
        return Objects.equals(toAdd, that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
