package duke.commands;

import java.util.Objects;

import duke.DukeException;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark task as uncompleted.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnmarkCommand)) {
            return false;
        }
        UnmarkCommand that = (UnmarkCommand) o;
        return indexToMark == that.indexToMark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexToMark);
    }
}
