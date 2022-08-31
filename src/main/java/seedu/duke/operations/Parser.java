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
     * @param cmd   User input
     * @return      Command
     */
    public static Command parse(String command) {
        command = command.strip();
        int separator = cmd.indexOf(' ');
        String keyWord = cmd;
        if (separator != -1) {
            keyWord = cmd.substring(0, separator);
        }
        switch (keyWord) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                return new MarkCommand(cmd);
            case "unmark":
                return new UnmarkCommand(cmd);
            case "delete":
                return new DeleteCommand(cmd);
            case "find":
                return new FindCommand(cmd.substring("find".length()).strip());
            case "todo":
                return new MakeTodoCommand(cmd.substring("todo".length()));
            case "deadline":
                return new MakeDeadlineCommand(cmd.substring("deadline".length()));
            case "event":
                return new MakeEventCommand(cmd.substring("event".length()));
            default:
                return new InvalidCommand();
        }
    }
}
