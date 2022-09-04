package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_FIND_KEYWORD;

import duke.chatbot.data.task.TaskList;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that prints a list of tasks that have the argument string as a substring of the task description.
 *
 * @author jq1836
 */
public class FindCommand extends Command {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "find";

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays a list of tasks which have the
     * argument string as a substring of the task description.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        TaskList filteredTaskList = taskList.filterTaskListBySubstring(arguments);
        if (filteredTaskList.isEmpty()) {
            message.buildLine(MESSAGE_EMPTY_LIST);
        } else {
            message.buildLine(MESSAGE_FIND_KEYWORD);
        }
        message.buildLine(filteredTaskList.toString());
        return new CommandResult(message.toString());
    }
}
