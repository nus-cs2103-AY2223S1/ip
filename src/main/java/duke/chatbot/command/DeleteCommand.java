package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_DELETED;

import java.util.ArrayList;
import java.util.List;

import duke.chatbot.data.task.Task;

/**
 * A command that deletes the targeted task from the
 * list of Task in the application. The targeted task
 * is chosen by an argument string with an integer in
 * the argument list.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of CommandResult with a message that displays
     * the task that was deleted.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MESSAGE_DELETED);

        int entryNo = Integer.parseInt(arguments.get(0));
        Task task = taskList.remove(entryNo);
        message.add(task.toString());
        return new CommandResult(message);
    }
}
