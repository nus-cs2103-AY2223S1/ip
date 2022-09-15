package duke.command;

import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printUnmarkTask(taskList.unmarkTask(index));
    }
}