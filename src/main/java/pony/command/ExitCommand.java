package pony.command;

import pony.Storage;
import pony.TaskList;
import pony.Ui;

/**
 * Command for exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand.
     */
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Executes an Exit command.
     *
     * @param tasks TaskList that stores Tasks.
     * @param storage Storage that handles memory files.
     * @param ui Ui that handles interaction with users.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printExit();
    }
}
