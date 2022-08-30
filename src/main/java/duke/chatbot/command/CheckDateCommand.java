package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_CHECK_DATE;
import static duke.chatbot.common.Message.MESSAGE_EMPTY_LIST;

import java.util.ArrayList;
import java.util.List;

import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;

/**
 * A command that prints a list of TimedTask that have the same
 * date as the date argument input.
 * @author Jordan Quah Shao Xuan
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
        List<String> message = new ArrayList<>();
        TaskList filteredTaskList = taskList.filterTaskListByDate(arguments.get(0));

        if (filteredTaskList.isEmpty()) {
            message.add(MESSAGE_EMPTY_LIST);
        } else {
            message.add(MESSAGE_CHECK_DATE);
        }

        for (int entry = 1; entry <= filteredTaskList.size(); entry++) {
            Task task = filteredTaskList.get(entry);
            message.add(entry + ". " + task.toString());
        }
        return new CommandResult(message);
    }
}
