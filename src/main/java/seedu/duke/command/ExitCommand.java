package seedu.duke.command;

import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Class to execute exit command
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        super.isExit = true;
    }

    /**
     *
     * @param list
     */
    @Override
    public void execute(TaskList list) {
        Ui.exit();
    }
}
