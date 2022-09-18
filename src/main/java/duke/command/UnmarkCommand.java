package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command{

    String taskName;

    public UnmarkCommand(String taskName) {
        this.taskName = taskName;
    }

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        boolean isSuccess = taskList.unmarkDone(this.taskName);
        return ui.showUnmarkMessage(isSuccess, this.taskName);
    };

}
