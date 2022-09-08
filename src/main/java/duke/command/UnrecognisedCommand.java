package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
