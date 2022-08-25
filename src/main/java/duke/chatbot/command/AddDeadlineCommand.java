package duke.chatbot.command;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Deadline;
import duke.chatbot.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute() throws InvalidInputException {
        List<String> message = new ArrayList<>();
        Deadline task = new Deadline(arguments.get(0), Parser.parseDateTime(arguments.get(1)));
        taskList.add(task);
        message.add(MESSAGE_ADDED_TASK);
        message.add(task.toString());
        return new CommandResult(message);
    }
}
