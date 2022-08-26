package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ErrorCommand extends Command {

    private String errorMessage;

    public ErrorCommand(String errorMessage) {
        super(CommandType.ERROR);
        this.errorMessage = errorMessage;
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        ui.printOutput(errorMessage);
    }
}
