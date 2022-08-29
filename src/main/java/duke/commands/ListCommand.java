package duke.commands;

import duke.Message;
import duke.task.TaskList;

/**
 * Command to list all existing tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";


    @Override
    public Message execute(TaskList tasks) {
        return new Message("Here are the tasks in your list:\n" + tasks.toString(),
                false,
                Message.User.DUKE);
    }
}
