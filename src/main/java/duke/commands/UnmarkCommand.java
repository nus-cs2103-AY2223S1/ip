package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Mark task as uncompleted.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "Ok, I've marked this task as not done yet:\n  %s";

    private final int indexToMark;

    public UnmarkCommand(int task) {
        this.indexToMark = task - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.setCompletion(this.indexToMark, false);
        ui.displayText(MESSAGE_SUCCESS, task);
    }
}
