package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to view the schedule
 */
public class ViewCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return tasks.getScheduleView();
    }
}
