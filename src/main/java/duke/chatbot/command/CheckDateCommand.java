package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_CHECK_DATE;
import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;

import java.util.List;

import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that prints a list of TimedTask that have the same
 * date as the date argument input.
 * @author jq1836
 */
public class CheckDateCommand extends Command {
    public CheckDateCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of CommandResult with a message that displays
     * a list of TimedTask which have the same date as the argument input.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        TaskList filteredTaskList = taskList.filterTaskListByDate(arguments.get(0));

        if (filteredTaskList.isEmpty()) {
            message.addLines(MESSAGE_EMPTY_LIST);
        } else {
            message.addLines(MESSAGE_CHECK_DATE);
        }

        for (int entry = 1; entry <= filteredTaskList.size(); entry++) {
            Task task = filteredTaskList.get(entry);
            message.addLines(entry + ". " + task.toString());
        }

        return new CommandResult(message.toString());
    }
}
