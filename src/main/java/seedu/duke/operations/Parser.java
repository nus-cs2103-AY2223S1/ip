package seedu.duke.operations;

import seedu.duke.command.*;

/**
 * Parser parses the user input and returns the relevant
 * Command to execute.
 */
public class Parser {

    /**
     * Returns a Command after parsing user input.
     *
     * @param command   User input
     * @return      Command
     */
    public static Command parse(String command) {
        command = command.strip();
        int separator = command.indexOf(' ');
        String keyWord = command;
        if (separator != -1) {
            keyWord = command.substring(0, separator);
        }
        switch (keyWord) {
            case "list":
                return new ListCommand();
            case "help":
                return new HelpCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                return new MarkCommand(command);
            case "unmark":
                return new UnmarkCommand(command);
            case "delete":
                return new DeleteCommand(command);
            case "find":
                return new FindCommand(command.substring("find".length()).strip());
            case "todo":
                return new MakeTodoCommand(command.substring("todo".length()));
            case "deadline":
                return new MakeDeadlineCommand(command.substring("deadline".length()));
            case "event":
                return new MakeEventCommand(command.substring("event".length()));
            default:
                return new InvalidCommand();
        }
    }
}
