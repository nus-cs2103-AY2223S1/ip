package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_CHECK_DATE;
import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;

import duke.chatbot.data.task.TaskList;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that prints a list of TimedTask that have the same date as the date argument input.
 *
 * @author jq1836
 */
public class CheckDateCommand extends Command {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "check";

    public CheckDateCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays a list of TimedTask which have the
     * same date as the argument input.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        TaskList filteredTaskList = taskList.filterTaskListByDate(arguments);
        if (filteredTaskList.isEmpty()) {
            message.buildLine(MESSAGE_EMPTY_LIST);
        } else {
            message.buildLine(MESSAGE_CHECK_DATE);
        }
        message.buildLine(filteredTaskList.toString());
        return new CommandResult(message.toString());
    }
}
