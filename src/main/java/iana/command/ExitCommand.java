package iana.command;

import iana.exception.IanaException;
import iana.main.Storage;
import iana.main.Ui;
import iana.tasks.TaskList;

/**
 * Command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Runs the command to exit the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.write(tasks);
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        }
        ui.sayBye();
    }

    /**
     * Returns true as command is exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}