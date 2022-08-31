package duke.command;

import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {

    public String execute(TaskList tasks, Storage storage) {
        return "Cya!";
    }

    public boolean isExit() {
        return true;
    }
}
