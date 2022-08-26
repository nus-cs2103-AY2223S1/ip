package commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    private Ui ui;
    private TaskList tasks;
    private int target;
    private boolean isMark;
    public MarkCommand(Ui ui, TaskList tasks, int target, boolean isMark) {
        this.ui = ui;
        this.tasks = tasks;
        this.target = target;
        this.isMark = isMark;
    }

    public void execute() throws DukeException {
        if (target >= tasks.size()) {
            throw new DukeException("There is no item numbered " + target + ".");
        }
        Task toMark = tasks.get(target);
        if (isMark) {
            toMark.mark();
            this.ui.showMarked(toMark);
        } else {
            toMark.unmark();
            this.ui.showUnmarked(toMark);
        }
    }
}
