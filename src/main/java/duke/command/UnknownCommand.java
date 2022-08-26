package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class UnknownCommand extends Command {

    private static final String GENERAL_ERROR_STRING = "Sorry, I don't understand that!";

    UnknownCommand() {
        super(CommandType.UNKNOWN);
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        ui.printOutput(GENERAL_ERROR_STRING);
    }
}
