package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(CommandType.BYE);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }
}
