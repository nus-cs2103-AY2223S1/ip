package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ChangeDateFormatCommand extends Command {
    private int index;

    public ChangeDateFormatCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String read(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.changeDateFormat(index).equals("")) {
            return ui.getNoDateMessage();
        } else {
            return ui.getDateChangedMessage() + "\n" + taskList.changeDateFormat(index);
        }
    }
}