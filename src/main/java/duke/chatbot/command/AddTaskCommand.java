package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;

import duke.chatbot.data.task.Task;
import duke.chatbot.util.MessageBuilder;

/**
 * Encapsulates a {@link Command} that adds a task to the application's task list.
 *
 * @author jq1836
 */
public abstract class AddTaskCommand extends Command {
    protected void addTask(Task task) {
        taskList.add(task);
    }

    protected CommandResult getResult(Task task) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.buildLines(MESSAGE_ADDED_TASK, task.toString());
        return new CommandResult(messageBuilder.toString());
    }
}
