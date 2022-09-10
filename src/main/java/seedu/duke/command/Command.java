package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;

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
