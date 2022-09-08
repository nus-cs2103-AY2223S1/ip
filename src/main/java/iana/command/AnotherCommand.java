package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that requests user for a new command.
 */
public class AnotherCommand extends Command {

    /**
     * Ask user to input a new command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.askNewCommand();
    }

    /**
     * Returns false as command is not exit. 
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
