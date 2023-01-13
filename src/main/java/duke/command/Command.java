package duke.command;

import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public abstract String execute(TaskList tasks, Storage storage);

    public boolean isExit() {
        return false;
    };

}
