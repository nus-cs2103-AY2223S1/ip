package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public static final String COMMAND_NAME = "bye";

    public ByeCommand() {

    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isTerminator() {
        return true;
    }
}
