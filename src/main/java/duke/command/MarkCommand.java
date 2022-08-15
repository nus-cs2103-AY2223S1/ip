package duke.command;

import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    int idx;

    public MarkCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showMark(tasks.markTask(this.idx));
    }
}