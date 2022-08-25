package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public static final boolean IS_EXIT = true;
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        return this.IS_EXIT;
    }
}
