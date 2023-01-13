package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;

/**
 * Command class is an abstract class that is inherited by specific commands.
 */
public abstract class Command {
    /**
     * Another constructor for the class Command.
     */
    public Command() {
    }

    /**
     * Runs the command and creates a task if the arguments are valid.
     *
     * @param tasks TaskList that stores the list of tasks.
     * @param ui Ui that handles users' interaction.
     * @param storage Storage that saves users' previous entries.
     * @return Output string to be displayed on the GUI.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the user has terminated the program, otherwise false.
     */
    public abstract boolean isExit();
}
