package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list the contents of task list.
 */
public class ListCommand extends Command {
    private static final ListCommand LIST_COMMAND = new ListCommand();

    /**
     * Constructor for ListCommand class.
     */
    private ListCommand() {
        super(false);
    }

    /**
     * Returns the list command.
     *
     * @return list command.
     */
    public static ListCommand of() {
        return LIST_COMMAND;
    }

    /**
     * Tells user interface to print task list.
     *
     * @param taskList task list.
     * @param ui user interface of program.
     * @param storage files storing task list.
     * @throws DukeException if list is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showListOut(taskList);
    }
}
