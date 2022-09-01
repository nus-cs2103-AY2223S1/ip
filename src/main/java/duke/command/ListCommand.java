package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int len = tasks.getLength();
        ui.showOutput("You currently have " + len + " tasks:");
        ui.showOutput(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
