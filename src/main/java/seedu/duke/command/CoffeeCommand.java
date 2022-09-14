package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;

public class CoffeeCommand extends Command {
    public CoffeeCommand() {
        super();
    }

    @Override
    public String execute(TaskList list) throws DukeException {
        return Ui.makeCoffee();
    }
}
