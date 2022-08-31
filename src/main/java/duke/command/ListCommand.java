package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     * @param commandOutputs       user interface of program.
     * @param storage  files storing task list.
     * @return
     * @throws DukeException if list is empty.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage) throws DukeException {
        return commandOutputs.showListOut(taskList);
    }
}
