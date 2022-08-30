package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to exit program.
 */
public class ExitCommand extends Command {
    /**
     * ExitCommand constructor.
     */
    public ExitCommand() {
    }

    /**
     * Executes a Exit command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printExit();
    }

    /**
     * Checks if program should exit.
     *
     * @return true as program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
