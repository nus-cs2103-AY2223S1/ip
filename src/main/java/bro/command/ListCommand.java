package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * ListCommand class.
 */
public class ListCommand extends Command {

    /**
     * Constructor of the ListCommand class.
     */
    public ListCommand() {}

    /**
     * {@inheritDoc}
     *
     * Lists all the tasks.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return tasklist.listAll();
    }
}
