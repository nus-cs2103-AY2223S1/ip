package duke.command;

import duke.DukeException;
import duke.TaskList;

public class SortCommand extends Command {
    public String execute(TaskList tasks) throws DukeException {
        return tasks.sort();
    }
}
