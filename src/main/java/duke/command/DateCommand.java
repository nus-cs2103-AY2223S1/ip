package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;


public class DateCommand extends Command {
    private String dateStr;

    public DateCommand(String dateStr) throws DukeException {
        super();
        if (dateStr.length() == 0) {
            throw new DukeException("Oops, no date given.");
        }
        this.dateStr = dateStr;
    }

    @Override
    public String executeWithMessage(TaskList tasks) throws DukeException {
        return tasks.getDateTasks(this.dateStr);
    }
}