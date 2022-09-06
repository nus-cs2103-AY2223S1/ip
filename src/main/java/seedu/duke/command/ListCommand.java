package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

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
    public void execute(TaskList list) throws DukeException {
        Ui.printList(list);
    }
}
