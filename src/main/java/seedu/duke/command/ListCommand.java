package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;
import seedu.duke.Ui.Ui;

/**
 * Class to execute list command
 */
public class ListCommand extends Command {

    public ListCommand() {

    }

    /**
     * Prints the list
     * @param list
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        return Ui.printList(list);
    }
}
