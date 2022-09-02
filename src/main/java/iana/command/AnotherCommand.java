package iana.command;

import iana.main.Ui;
import iana.tasks.TaskList;

/**
 * Command that requests user for a new command.
 */
public class AnotherCommand extends Command {

    /**
     * Ask user to input a new command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.askNewCommand();
    }

    /**
     * Returns false as command is not exit. 
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
