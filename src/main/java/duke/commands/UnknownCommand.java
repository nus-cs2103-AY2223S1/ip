package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class UnknownCommand extends Command {

    private static final String MESSAGE = "\tI am sorry, but I do " +
            "not understand this command";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayMessage(MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}