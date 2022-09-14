package duke.commands;

import java.util.List;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;

/**
 * Parses the command that is input by user and returns the corresponding handler based on the command given.
 */
public class CommandHandlerFactory {
    public CommandHandler getHandler(String input) throws DukeException {
        Parser parser = new Parser();
        Command command = parser.getCommand(input);

        List<String> args = parser.parseCommand(input);
        String value = args.get(0);
        String flag = args.get(1);
        String additionalValue = args.get(2);

        switch(command) {
        case LIST:
            return new CommandListHandler(value, flag, additionalValue);
        case MARK:
            return new CommandMarkHandler(value, flag, additionalValue);
        case UNMARK:
            return new CommandUnmarkHandler(value, flag, additionalValue);
        case TODO:
            return new CommandTodoHandler(value, flag, additionalValue);
        case DEADLINE:
            return new CommandDeadlineHandler(value, flag, additionalValue);
        case EVENT:
            return new CommandEventHandler(value, flag, additionalValue);
        case DELETE:
            return new CommandDeleteHandler(value, flag, additionalValue);
        case FIND:
            return new CommandFindHandler(value, flag, additionalValue);
        case BYE:
            return new CommandByeHandler(value, flag, additionalValue);
        default:
            throw new InvalidSyntaxException("No such command!");
        }
    }
}
