package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public String execute(TaskList tasks, Storage storage) {
        Ui.exit();
        return "Cya!";
    }

    public boolean isExit() {
        return true;
    }
}
