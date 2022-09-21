package catbot.command;

import catbot.TaskList;
import catbot.Ui;
/**
 * A class for the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Returns as there is nothing to execute for bye command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        this.response = "bye";
    }

}
