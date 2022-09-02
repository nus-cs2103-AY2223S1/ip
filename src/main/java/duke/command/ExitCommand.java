package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** Represents the command to end the Duke program. */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printFarewellMessage();
        storage.saveTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
