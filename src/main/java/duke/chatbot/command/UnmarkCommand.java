package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_UNMARKED;

import java.util.ArrayList;
import java.util.List;

import duke.chatbot.data.task.Task;

/**
 * A command that unmarks the targeted task from the
 * list of Task in the application. The targeted
 * is chosen by an argument string with an integer in
 * the argument list.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of CommandResult with a message that displays the
     * task unmarked.
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MESSAGE_UNMARKED);

        int entryNo = Integer.parseInt(arguments.get(0));
        Task task = taskList.get(entryNo);
        task.unmark();
        message.add(task.toString());
        return new CommandResult(message);
    }
}
