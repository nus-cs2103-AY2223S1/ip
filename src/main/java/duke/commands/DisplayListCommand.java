package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command for displaying list of Tasks.
 */
public class DisplayListCommand extends Command {
    /**
     * DisplayListCommand constructor.
     */
    public DisplayListCommand() {
    }

    /**
     * Executes a DisplayList command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String listToString = tasks.listToString();
        ui.printDisplayList(listToString);
    }

    /**
     * Checks if program should exit.
     *
     * @return false as program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
