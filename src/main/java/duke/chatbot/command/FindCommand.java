package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_FIND_KEYWORD;

import java.util.List;

import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that prints a list of tasks that have the argument
 * string as a substring of the task description.
 * @author jq1836
 */
public class FindCommand extends Command {
    public FindCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of CommandResult with a message that displays
     * a list of tasks which have the argument string as a substring of the
     * task description.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        String query = arguments.get(0);
        TaskList filteredTaskList = taskList.filterTaskListBySubstring(query);

        if (filteredTaskList.isEmpty()) {
            message.addLines(MESSAGE_EMPTY_LIST);
        } else {
            message.addLines(MESSAGE_FIND_KEYWORD);
        }

        for (int entry = 1; entry <= filteredTaskList.size(); entry++) {
            Task task = filteredTaskList.get(entry);
            message.addLines(entry + ". " + task.toString());
        }

        return new CommandResult(message.toString());
    }
}
