package duke.commands;

import duke.Message;
import duke.task.TaskList;

/**
 * Command to list all existing tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String TASK_LIST_MESSAGE = "Here are the tasks in your list:\n";


    @Override
    public Message execute(TaskList tasks) {
        return new Message(TASK_LIST_MESSAGE + tasks.toString(),
                false,
                Message.User.DUKE);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
