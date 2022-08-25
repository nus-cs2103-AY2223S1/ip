package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class WrongCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printBorder();
        String message = "Please enter some valid duke.command.Command";
        ui.displayCommandMessage(message, null, null);
        ui.printBorder();
    }
}
