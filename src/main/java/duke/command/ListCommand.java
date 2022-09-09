package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.TaskList;

/**
 * A command to list all the tasks in the task list.
 * Usage: list
 */
public class ListCommand extends Command {
    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        if (tasks.size() == 0) {
            messageBuilder.addLine("You do not have any tasks at the moment.");
        } else {
            messageBuilder.addLine("Here are your tasks!").addLine(tasks.toString());
        }
    }
}
