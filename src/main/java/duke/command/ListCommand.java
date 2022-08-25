package duke.command;

import duke.component.TaskList;
import duke.exception.DukeException;

public class ListCommand extends Command {

    public static final String MSG_LIST = "Here are the tasks in your list:\n";


    public ListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String run() throws DukeException {
        return MSG_LIST + this.tasks.toString();
    }

}