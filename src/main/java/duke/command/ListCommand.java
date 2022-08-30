package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Print and list all the Task in the TaskList.
 */
public class ListCommand extends Command {
    public static final boolean IS_EXIT = false;

    /**
     * Constructs a ListCommand instance without initiating any parameter.
     */
    public ListCommand() {
    }

    /**
     * Passes the TaskList to Ui to print all the Tasks in it.
     *
     * @param taskList the TaskList to be passed into Ui and to be printed.
     * @param ui       The Ui provides printList method to allow TaskList to be printed.
     * @param storage  unused for EmptyCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getList(taskList);
    }

    /**
     * Returns false as List is not a terminating Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return this.IS_EXIT;
    }
}
