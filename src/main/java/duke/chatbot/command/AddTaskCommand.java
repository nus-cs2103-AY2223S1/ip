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
    /**
     * Adds an instance of {@link Task} to the list.
     *
     * @param task The task to be added to the list.
     */
    protected void addTask(Task task) {
        assert(taskList != null);
        taskList.add(task);
    }

    protected String buildMessage(Task task) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.buildLines(MESSAGE_ADDED_TASK, task.toString());
        return messageBuilder.toString();
    }
}
