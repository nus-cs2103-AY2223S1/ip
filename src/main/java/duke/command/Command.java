package duke.command;

import duke.DukeException;
import duke.TaskList;

public class Command {
    boolean isExit = false;

    public Command() {}

    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList list) throws DukeException {}
}
