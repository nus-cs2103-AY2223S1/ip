package clevernotbot;

/**
 * Represents a command used for greeting
 */
public class GreetCommand extends Command {

    /**
     * Constructor for the GreetCommand.
     *
     * @param commandName Description of command.
     * @param exit Checking if program intends to exit.
     */
    public GreetCommand(String commandName, boolean exit){
        super(commandName,exit);
    }

    /**
     * Runs the greet command.
     *
     * @param tasks The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        textBox.chat("Hello! I'm CleverNotBot\n What can I do for you?");
    }
}
