package duke.commands;

import duke.exceptions.DukeException;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs the required actions for Duke to list out all the tasks stored.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command from the user.
     *
     * @param taskList The list of tasks stored by the user.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.sayList();
            for (int i = 0; i < taskList.getSize(); i++) {
                ui.sayTaskWithIndex(i, taskList.getTask(i));
            }
            ui.sayFinishListing();
        } catch (DukeException e) {
            ui.sayExceptionMessage(e);
        }
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ListCommand) {
            return true;
        }
        return false;
    }
}
