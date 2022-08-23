package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Event;

import java.util.ArrayList;
import java.util.List;

public class AddEventCommand extends Command {
    public AddEventCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();

        try {
            System.out.println("1" + arguments.get(1) + "1");
            Event task = new Event(arguments.get(0), arguments.get(1));
            taskList.add(task);
            message.add(MessageConstants.MESSAGE_ADDED_TASK);
            message.add(task.toString());
            return new CommandResult(message);
        } catch (InvalidInputException e) {
            return new InvalidInputCommand().execute();
        }
    }
}
