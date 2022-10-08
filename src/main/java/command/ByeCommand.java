package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.TaskList;

/**
 * Represents a command used for saying bye.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for the ByeCommand.
     *
     * @param commandName Description of Command.
     * @param exit        Checking if program intends to exit.
     */
    public ByeCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the bye command.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        return "Bye. Hope to see you again soon!";
    }
}
