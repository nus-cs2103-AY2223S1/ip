package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int indexToMark;

    public MarkCommand(int indexToMark) {
        super();
        this.indexToMark = indexToMark;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(indexToMark).markAsDone();
        ui.showMarkMessage(taskList.getTask(indexToMark));
    }
}
