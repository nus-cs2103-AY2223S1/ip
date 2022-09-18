package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    String taskName;

    public MarkCommand(String taskName) {
        this.taskName = taskName;
    }

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        boolean isSuccess = taskList.markDone(this.taskName);
        return ui.showMarkMessage(isSuccess, this.taskName);
    };

}
