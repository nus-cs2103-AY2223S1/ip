package duke.commands;

import duke.Message;
import duke.task.TaskList;

/**
 * Command to terminate the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public Message execute(TaskList tasks) {
        return new Message(GOODBYE_MESSAGE, true, Message.User.DUKE);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
