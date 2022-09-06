package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class UnrecognisedCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.display("Sorry, I don't recognise that command :(");
    }

}
