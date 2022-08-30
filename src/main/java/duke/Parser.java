package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;
import duke.task.Deadline;


/**
 * Parser for all user inputs.
 */
public class Parser {
    public static final Pattern TASK_FORMAT = Pattern.compile(getNumberRegex("taskNumber"));
    public static final String COMMAND_NOT_FOUND_MESSAGE = "Invalid command.";

    public static String getNumberRegex(String name) {
        return "(?<" + name + ">\\d+)";
    }
    public static String getTextRegex(String name) {
        return "(?<" + name + ">[^/]+)";
    }

    public static String getDateTimeRegex(String name) {
        return "(?<" + name + ">\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2})";
    }

    public static String combineRegexes(String... regexes) {
        return String.join("\\s+", regexes);
    }


    /**
     * Parses the user input and return the corresponding command.
     *
     * @param inputString input from user.
     * @return command object.
     * @throws DukeException exception thrown if the input is invalid.
     */
    public static Command parse(String inputString) throws DukeException {
        final Matcher matcher = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)").matcher(inputString.trim());
        try {
            if (matcher.matches()) {
                switch (Commands.getCommand(matcher.group("commandWord"))) {
                case BYE:
                    return new ExitCommand();
                case FIND:
                    return processFind(matcher.group("arguments").strip());
                case LIST:
                    return new ListCommand();
                case TODO:
                    return processToDo(matcher.group("arguments").strip());
                case EVENT:
                    return processEvent(matcher.group("arguments").strip());
                case DEADLINE:
                    return processDeadline(matcher.group("arguments").strip());
                case MARK:
                    return processMark(matcher.group("arguments").strip(), true);
                case UNMARK:
                    return processMark(matcher.group("arguments").strip(), false);
                case DELETE:
                    return processDelete(matcher.group("arguments").strip());
                default:
                    return new InvalidCommand(COMMAND_NOT_FOUND_MESSAGE);
                }
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand(COMMAND_NOT_FOUND_MESSAGE);
        }
        return new InvalidCommand(COMMAND_NOT_FOUND_MESSAGE);
    }

    private static Command processFind(String inputString) {
        return new FindCommand(preprocessString(inputString));
    }

    private static Command processToDo(String inputString) {
        return new ToDoCommand(preprocessString(inputString));
    }

    private static Command processEvent(String inputString) {
        final Matcher matcher = Pattern.compile(combineRegexes(getTextRegex("description"),
                        "/at",
                        getTextRegex("eventTime")))
                .matcher(inputString.strip());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid event format");
        }
        return new EventCommand(matcher.group("description").strip(),
                matcher.group("eventTime").strip());
    }

    private static Command processDeadline(String inputString) {
        final Matcher matcher = Pattern.compile(combineRegexes(getTextRegex("description"),
                        "/by",
                        getDateTimeRegex("datetime")))
                .matcher(inputString.strip());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid deadline format");
        }

        return new DeadlineCommand(matcher.group("description").strip(),
                LocalDateTime.parse(matcher.group("datetime").strip(),
                        DateTimeFormatter.ofPattern(Deadline.EVENT_DATETIME_FORMAT)));
    }


    private static String preprocessString(String rawInput) {
        return rawInput.strip();
    }

    private static Command processMark(String inputString, boolean completed) {
        final Matcher matcher = TASK_FORMAT.matcher(inputString.strip());
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid mark/unmark format");
        }
        try {
            int taskNumber = Integer.parseInt(matcher.group("taskNumber").strip());
            if (completed) {
                return new MarkCommand(taskNumber);
            } else {
                return new UnmarkCommand(taskNumber);
            }
        } catch (NumberFormatException e) {
            return new InvalidCommand("ID cannot be parsed.");
        }
    }

    private static Command processDelete(String inputString) {
        final Matcher matcher = TASK_FORMAT.matcher(inputString.strip());
        if (!matcher.matches()) {
            return new InvalidCommand("Failed to delete task %s", inputString);
        }
        try {
            int taskNumber = Integer.parseInt(matcher.group("taskNumber").strip());
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Failed to delete task %s", inputString);
        }
    }


    /**
     * Lists all valid commands
     */
    public enum Commands {
        TODO(ToDoCommand.COMMAND_WORD),
        EVENT(EventCommand.COMMAND_WORD),
        DEADLINE(DeadlineCommand.COMMAND_WORD),
        MARK(MarkCommand.COMMAND_WORD),
        UNMARK(UnmarkCommand.COMMAND_WORD),
        DELETE(DeleteCommand.COMMAND_WORD),
        LIST(ListCommand.COMMAND_WORD),
        BYE(ExitCommand.COMMAND_WORD),
        FIND(FindCommand.COMMAND_WORD);
        public final String commandWord;

        Commands(String commandWord) {
            this.commandWord = commandWord;
        }

        public static Commands getCommand(String command) throws IllegalArgumentException {
            for (Commands c : Commands.values()) {
                if (c.commandWord.compareToIgnoreCase(command) == 0) {
                    return c;
                }
            }
            throw new IllegalArgumentException("Invalid command");
        }
    }
}
