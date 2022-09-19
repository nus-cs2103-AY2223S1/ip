package iana.command;

import iana.exception.IanaException;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that shows all available commands.
 */
public class HelpCommand extends Command {
    
    @Override
    public String execute(TaskList tasks, Ui ui) throws IanaException {
        return ui.help();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
