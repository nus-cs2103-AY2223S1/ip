package commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command for marking a Task.
 */
public class MarkCommand extends Command {

    private Ui ui;
    private TaskList tasks;
    private int target;
    private boolean isMark;

    /**
     * Returns a new MarkCommand.
     * @param ui User interface to print a message to the user.
     * @param tasks TaskList from which a task will be marked or unmarked.
     * @param target Task to be marked or unmarked.
     * @param isMark A boolean designating a Task to be marked or unmarked.
     */
    public MarkCommand(Ui ui, TaskList tasks, int target, boolean isMark) {
        this.ui = ui;
        this.tasks = tasks;
        this.target = target;
        this.isMark = isMark;
    }

    /**
     * Executes the command, and returns a String
     * describing the executing of this Command.
     * @return A String describing the Task that was marked or unmarked.
     * @throws DukeException An exception thrown when the target Task does not exist.
     */
    public String execute() throws DukeException {
        if (target >= tasks.size()) {
            throw new DukeException("There is no item numbered " + target + ".");
        }
        Task toMark = tasks.get(target);
        if (isMark) {
            toMark.mark();
            return this.ui.showMarked(toMark);
        } else {
            toMark.unmark();
            return this.ui.showUnmarked(toMark);
        }
    }
}
