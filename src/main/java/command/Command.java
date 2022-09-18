package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {
    private String commandName;
    private boolean isExit;

    /**
     * Constructor for the Command.
     *
     * @param commandName Description of Command.
     * @param isExit        Checking if program intends to exit.
     */
    public Command(String commandName, boolean isExit) {
        this.commandName = commandName;
        this.isExit = isExit;
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
        return isExit;
    }

    /**
     * Runs the command that it is supposed to.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception.
     */
    public abstract String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException;
}
