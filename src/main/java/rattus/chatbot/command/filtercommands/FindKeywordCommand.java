package rattus.chatbot.command.filtercommands;

import java.util.function.Predicate;

import rattus.chatbot.data.task.Task;

/**
 * A command that prints a list of tasks that have the argument string as a substring of the task description.
 *
 * @author jq1836
 */
public class FindKeywordCommand extends FilterCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "find";

    public FindKeywordCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    Predicate<Task> supplyCondition() {
        return (task) -> task.hasSubstring(arguments);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof FindKeywordCommand;
    }
}
