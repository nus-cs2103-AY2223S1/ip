package duke.commands;

import duke.Message;
import duke.task.TaskList;

/**
 * Command to terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public Message execute(TaskList tasks) {
        return new Message("Bye. Hope to see you again soon!", true, Message.User.DUKE);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineCommand;
    }
}
