package catbot.command;

import catbot.TaskList;
import catbot.Ui;
import catbot.exception.CatBotException;

/**
 * An abstract class for commands.
 */
public abstract class Command {

    /** The text response after the command has been executed to be displayed. */
    String response = "Meow! ";

    /**
     * Executes the respective command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws CatBotException If there are any errors that cause exceptions to be thrown.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws CatBotException;

    /**
     * Returns the response after the command has been executed.
     *
     * @return The response after the command has been executed.
     */
    public String response() {
        return this.response;
    }
}
