package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Class to encapsulate a command that prints the list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Orders for the printing of all tasks in list.
     *
     * @param ui The user interface.
     * @param tasks The list of tasks to print from.
     * @param storage The local storage file.
     *
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.printList();
    }

}
