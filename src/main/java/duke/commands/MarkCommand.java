package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Mark task as completed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n  %s";

    private final int indexToMark;

    public MarkCommand(int task) {
        this.indexToMark = task - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.setCompletion(this.indexToMark, true);
        ui.displayText(MESSAGE_SUCCESS, task);
    }
}
