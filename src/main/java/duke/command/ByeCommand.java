package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        ui.showGoodbye();
    }
}
