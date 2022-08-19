abstract public class CommandParser {
    private static final String DEADLINE_INDICATOR = "\\s*/by\\s*";
    private static final String EVENT_INDICATOR = "\\s*/at\\s*";

    public static Command parse(String input) throws DukeException {
        input = input.strip();
        String inputCommand = input.indexOf(" ") == -1 ?
                input.toLowerCase() : input.toLowerCase().substring(0, input.indexOf(" "));
        String[] commandAndArguments = input.split(" ", 2);
        String[] args = new String[0];

        switch (inputCommand) {
        case "help":
            return new Command.HelpCommand(args);
        case "bye":
            return new Command.ByeCommand(args);
        case "list":
            return new Command.ListCommand(args);
        case "mark":
            return new Command.MarkCommand(args);
        case "unmark":
            return new Command.UnmarkCommand(args);
        case "todo":
            return new Command.ToDoCommand(args);
        case "event":
            args = parseEventArguments(commandAndArguments);
            return new Command.EventCommand(args);
        case "deadline":
            args = parseDeadlineArguments(commandAndArguments);
            return new Command.DeadlineCommand(args);
        case "delete":
            return new Command.DeleteCommand(args);
        case "":
            return new Command.EmptyCommand(args);
        default:
            return new Command.UnknownCommand(args);
        }
    }

    private static String[] parseDeadlineArguments(String[] commandAndArguments) throws DukeException {
        String formatErrorMessage = "Invalid format for deadline command";
        return parseArgumentsWithDelimiter(commandAndArguments, DEADLINE_INDICATOR, formatErrorMessage);
    }

    private static String[] parseEventArguments(String[] commandAndArguments) throws DukeException {
        String formatErrorMessage = "Invalid format for event command";
        return parseArgumentsWithDelimiter(commandAndArguments, EVENT_INDICATOR, formatErrorMessage);
    }

    private static String[] parseArgumentsWithDelimiter(String[] commandAndArguments, String delimiter,
                                                        String formatErrorMessage) throws DukeException {
        if (commandAndArguments.length <= 1) {
            throw new DukeException(formatErrorMessage);
        }
        String[] args = commandAndArguments[1].split(delimiter, 2);
        if (args.length <= 1) {
            throw new DukeException(formatErrorMessage);
        }
        return stripArguments(args);
    }

    private static String[] stripArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].strip();
        }
        return args;
    }
}
