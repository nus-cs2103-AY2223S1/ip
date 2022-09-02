package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

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
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.listAll();
    }
}
