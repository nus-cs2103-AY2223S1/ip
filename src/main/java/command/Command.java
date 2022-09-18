package command;

import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {
    private String commandName;
    private boolean exit;

    /**
     * Constructor for the Command.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public Command(String commandName, boolean exit) {
        this.commandName = commandName;
        this.exit = exit;
    }

    /**
     * Returns name of Command.
     *
     * @return Name of Command.
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * Returns true if the program is intending to exit.
     *
     * @return Exit status.
     */
    public boolean isExitingProgram() {
        return exit;
    }

    /**
     * An abstract method of run
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception.
     */
    public abstract String run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException;
}
