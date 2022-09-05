package duke.chatbot.command;

import java.util.function.Predicate;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;

/**
 * A command that prints a list of tasks that have the argument string as a substring of the task description.
 *
 * @author jq1836
 */
public class FindKeywordCommand extends FilterCommand {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "find";

    public FindKeywordCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    Predicate<Task> getCondition() {
        return (task) -> task.hasSubstring(arguments);
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays a list of tasks which have the
     * argument string as a substring of the task description.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() throws InvalidInputException {
        return super.execute();
    }
}
