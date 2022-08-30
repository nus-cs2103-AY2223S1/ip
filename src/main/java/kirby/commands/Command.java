package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.Ui;
import kirby.exceptions.KirbyMissingArgumentException;

/**
 * Command class is an abstract class that is inherited by specific commands.
 */
public abstract class Command {
    /**
     * Constructor for the class Command.
     *
     * @param inputString Arguments of a command.
     */
    public Command(String inputString) {
        String command = inputString.split(" ")[0];
    }

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
     * @throws KirbyMissingArgumentException If arguments are not followed with the respective commands.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException;

    /**
     * Returns true if the user has terminated the program, otherwise false.
     */
    public abstract boolean isExit();
}
