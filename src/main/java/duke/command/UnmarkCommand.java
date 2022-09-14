package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        taskList.setNotCompleted(index);
        return ui.getUnmarkedTaskMessage() + taskList.readTask(index);
    }
}
