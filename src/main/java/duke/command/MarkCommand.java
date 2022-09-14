package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        taskList.setCompleted(index);
        return ui.getMarkedTaskMessage() + taskList.readTask(index);
    }
}
