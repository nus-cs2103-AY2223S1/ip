package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Cya!");
    }

    public boolean isClose() {
        return true;
    }
}
