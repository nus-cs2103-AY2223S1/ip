import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s?(?<arguments>.*)");
    private static final DukeException UNKNOWN_COMMAND = new DukeException("Unknown command!");

    public Command parseCommand(String userInput) throws DukeException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        String commandWord, arguments;
        if (matcher.matches()) {
            commandWord = matcher.group("commandWord");
            arguments = matcher.group("arguments");
        } else {
            throw Parser.UNKNOWN_COMMAND;
        }
        Command command;

        switch (commandWord) {
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand();
                break;
            case ListCommand.COMMAND_WORD:
                command = new ListCommand();
                break;
            case MarkCommand.COMMAND_WORD: {
                command = new MarkCommand(arguments);
                break;
            }
            case UnmarkCommand.COMMAND_WORD: {
                command = new UnmarkCommand(arguments);
                break;
            }
            case DeleteCommand.COMMAND_WORD: {
                command = new DeleteCommand(arguments);
                break;
            }
            case TodoCommand.COMMAND_WORD: {
                command = new TodoCommand(arguments);
                break;
            }
            case DeadlineCommand.COMMAND_WORD: {
                command = new DeadlineCommand(arguments);
                break;
            }
            case EventCommand.COMMAND_WORD: {
                command = new EventCommand(arguments);
                break;
            }
            default:
                throw Parser.UNKNOWN_COMMAND;
        }

        return command;
    }
}
