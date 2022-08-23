package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.time.LocalDateTime;

/**
 * Create new Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_SUCCESS = "Got it. I've added this deadline:\n  %s\nNow you have %d tasks in your list";
    public static final String MESSAGE_FAILURE = "Unable to add deadline.";

    private final Deadline toAdd;

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
}
