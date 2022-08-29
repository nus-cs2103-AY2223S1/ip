package ted;

import ted.command.ByeCommand;
import ted.command.Command;
import ted.command.DeadlineCommand;
import ted.command.DeleteCommand;
import ted.command.EventCommand;
import ted.command.FindCommand;
import ted.command.ListCommand;
import ted.command.MarkCommand;
import ted.command.TodoCommand;
import ted.command.UnknownCommand;
import ted.command.UnmarkCommand;
import ted.exception.TedException;

/**
 * A class to parse the command and argument from user
 * input
 */
public class Parser {
    /**
     * CommandEnum to indicate the set of command that can be
     * used by users
     */
    enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

        public static CommandEnum fromString(String command) {
            for (CommandEnum value : CommandEnum.values()) {
                if (value.toString().equalsIgnoreCase(command)) {
                    return value;
                }
            }

            return null;
        }
    }

    /**
     * Parse the command and construct the command
     * @param input
     * @return command that can be executed and parsed from user's input
     * @throws TedException
     */
    public static Command parse(String input) throws TedException {
        String[] inputArr = input.split(" ");
        if (inputArr.length <= 0) {
            throw new TedException("command cannot be parsed.");
        }

        String commandStr = inputArr[0].toLowerCase();
        String args = input.length() > commandStr.length() + 1
                ? input.substring(commandStr.length() + 1)
                : "";

        CommandEnum commandEnum = CommandEnum.fromString(commandStr);

        Command command = new UnknownCommand(args);
        if (commandEnum != null) {
            switch (commandEnum) {
            case BYE:
                command = new ByeCommand(args);
                break;
            case LIST:
                command = new ListCommand(args);
                break;
            case MARK:
                command = new MarkCommand(args);
                break;
            case UNMARK:
                command = new UnmarkCommand(args);
                break;
            case TODO:
                command = new TodoCommand(args);
                break;
            case DEADLINE:
                command = new DeadlineCommand(args);
                break;
            case EVENT:
                command = new EventCommand(args);
                break;
            case DELETE:
                command = new DeleteCommand(args);
                break;
            case FIND:
                command = new FindCommand(args);
                break;
            default:
                command = new UnknownCommand(args);
                break;
            }
        }

        return command;
    }
}
