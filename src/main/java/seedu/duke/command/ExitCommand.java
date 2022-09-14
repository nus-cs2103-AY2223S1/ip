package seedu.duke.command;

import seedu.duke.list.TaskList;
import seedu.duke.Ui.Ui;

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
    public String execute(TaskList list) {
        return Ui.exit();
    }
}
