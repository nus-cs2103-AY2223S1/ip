package catbot.command;

import catbot.TaskList;
import catbot.Ui;

/**
 * A class for the help command.
 */
public class HelpCommand extends Command {

    /**
     * Returns as there is nothing to execute for help command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        this.response += "Help can be found at this link! \"https://github.com/tyw2811/ip\"";
    }

}
