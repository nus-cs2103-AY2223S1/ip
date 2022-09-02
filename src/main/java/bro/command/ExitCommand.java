package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class ExitCommand extends Command {

    /**
     * Constructor of the ExitCommand class.
     */
    public ExitCommand() {}

    /**
     * {@inheritDoc}
     *
     * Bye message will be printed.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Tells whether to exit the loop or not.
     * @return The value true for isExit variable.
     */
    public boolean isExit(){
        return this.isExit = true;
    }
}
