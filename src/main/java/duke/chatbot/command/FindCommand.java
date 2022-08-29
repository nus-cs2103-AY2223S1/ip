package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;
import static duke.chatbot.common.Message.MESSAGE_FIND_KEYWORD;

import java.util.ArrayList;
import java.util.List;

import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;

/**
 * A command that prints a list of tasks that have the argument
 * string as a substring of the task description.
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
        List<String> message = new ArrayList<>();
        String query = arguments.get(0);
        TaskList filteredTaskList = taskList.filterTaskListBySubstring(query);

        if (filteredTaskList.isEmpty()) {
            message.add(MESSAGE_EMPTY_LIST);
        } else {
            message.add(MESSAGE_FIND_KEYWORD);
        }

        for (int entry = 1; entry <= filteredTaskList.size(); entry++) {
            Task task = filteredTaskList.get(entry);
            message.add(entry + ". " + task.toString());
        }

        return new CommandResult(message);
    }
}
