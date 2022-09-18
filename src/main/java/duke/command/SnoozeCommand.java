package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.Task;

public class SnoozeCommand extends Command{

    String taskName;
    String newDate;

    public SnoozeCommand(String taskName, String newDate) {
        this.taskName = taskName;
        this.newDate = newDate;
    }

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        Task updatedTask = taskList.updateDate(this.taskName, this.newDate);
        return ui.showSnoozeMessage(updatedTask);
    };
}
