package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;

/**
 * Class for executing Coffee command
 */
public class CoffeeCommand extends Command {
    public CoffeeCommand() {
        super();
    }

    /**
     * Makes the user a cup of coffee
     * @param list
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        return Ui.makeCoffee();
    }
}
