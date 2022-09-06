package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        super.isExit = true;
    }

    @Override
    public void execute(TaskList list) {
        Ui.exit();
    }
}
