package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.TaskList;


/**
 * Command to exit the program.
 * Usage: bye
 */
public class ByeCommand extends Command {
    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        storage.saveTasks(tasks);
        messageBuilder.addLine("Bye! Hope to see you again soon!")
                .addLine("This window will close automatically in 3 seconds.");
    }
}
