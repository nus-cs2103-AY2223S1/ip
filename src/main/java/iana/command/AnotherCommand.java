package iana.command;

import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Command that requests user for a new command.
 */
public class AnotherCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.askNewCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
