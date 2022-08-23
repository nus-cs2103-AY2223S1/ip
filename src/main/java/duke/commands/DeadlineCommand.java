package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Command for creating a Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this deadline:\n  %s\nNow you have %d tasks in your list";
    public static final String MESSAGE_FAILURE = "Unable to add deadline.";

    private final Deadline toAdd;

    /**
     * Create a Deadline Command with description and an event time.
     * @param description description of deadline
     * @param eventTime date time of deadline
     */
    public DeadlineCommand(String description, LocalDateTime eventTime) {
        this.toAdd = new Deadline(description, eventTime);
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
        if (!(o instanceof DeadlineCommand)) return false;
        DeadlineCommand that = (DeadlineCommand) o;
        return toAdd.equals(that.toAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toAdd);
    }
}
