package duke.command;

import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printMarkTask(taskList.markTask(index));
    }
}