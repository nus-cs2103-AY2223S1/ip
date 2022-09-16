package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;


public class DateCommand extends Command {
    private String dateStr;

    public DateCommand(String dateStr) {
        super();
        this.dateStr = dateStr;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.getDateTasks(this.dateStr);
    }
}