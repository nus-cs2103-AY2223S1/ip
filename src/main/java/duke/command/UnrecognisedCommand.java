package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * Represents a user command that is unrecognised.
 */
public class UnrecognisedCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.display("Sorry, I don't recognise that command :(");
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return "Sorry, I don't know what that means :(";
    }

}
