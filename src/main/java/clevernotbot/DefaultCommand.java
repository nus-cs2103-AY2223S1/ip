package clevernotbot;

/**
 * Represents a command used at default.
 */
public class DefaultCommand extends Command {

    /**
     * Constructor for the DefaultCommand.
     *
     * @param commandName Description of Command.
     * @param exit Checking if program intends to exit.
     */
    public DefaultCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    /**
     * Runs the default command.
     *
     * @param tasks The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        textBox.chat("This command doesn't exist!");
    }
}
