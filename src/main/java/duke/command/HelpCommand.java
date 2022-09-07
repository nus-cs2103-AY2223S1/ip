package duke.command;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Consumer;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Encapsulates a command to print the help message.
 */
public class HelpCommand extends Command {

    public static final String HELP_STRING = "- help:\n"
            + "Prints this help message.";

    @Override
    public void execute(Storage storage, Consumer<String> printer, TaskList tasks) {
        String[] helpMessages = {
            HelpCommand.HELP_STRING,
            AddTaskCommand.HELP_STRING,
            DeleteTaskCommand.HELP_STRING,
            ListCommand.HELP_STRING,
            UpdateStatusCommand.HELP_STRING,
            FindCommand.HELP_STRING,
            ExitCommand.HELP_STRING
        };

        printer.accept("Here are the command you can use:");
        StringJoiner sj = new StringJoiner("\n\n");
        Arrays.stream(helpMessages).forEach(sj::add);
        printer.accept(sj.toString());
    }
}
