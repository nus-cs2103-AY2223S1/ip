package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;

public class Command {
    boolean isExit = false;

    public Command() {}

    public boolean isExit() {
        return isExit;
    }

    public String execute(TaskList list) throws DukeException {
        return "";
    }
}
