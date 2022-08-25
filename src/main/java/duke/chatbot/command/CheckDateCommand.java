package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that prints a list of TimedTask that have the same
 * date as the date argument input.
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
        message.add(MessageConstants.MESSAGE_CHECK_DATE);

        TaskList timedTaskList = taskList.filterTaskListByDate(arguments.get(0));

        for (int entry = 1; entry <= timedTaskList.size(); entry++) {
            Task task = timedTaskList.get(entry);
            message.add(entry + ". " + task.toString());
        }

        return new CommandResult(message);
    }
}
