package ted;

import ted.command.*;
import ted.exception.TedException;

public class Parser {
    enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

        public static CommandEnum fromString(String command) {
            for (CommandEnum value : CommandEnum.values()) {
                if (value.toString().equalsIgnoreCase(command)) {
                    return value;
                }
            }

            return null;
        }
    }

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
            }
        }

        return command;
    }
}
