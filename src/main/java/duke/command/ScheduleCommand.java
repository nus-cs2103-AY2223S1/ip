package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ScheduleCommand extends Command {

    String date;

    public ScheduleCommand(String date) {
        this.date = date;
    }

    public String exec(TaskList taskList, Storage storage , Ui ui) throws DukeException {
        assert(ui != null);
        return ui.showSchedule(this.date, taskList.getTaskList());
    };

}