package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.ByCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.CorruptedLineException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser to convert raw String to processed information.
 */
public final class Parser {
    private static final String SPACE = " +";
    // private static final String SEP = " +(/by|/at) +";
    private static final String DEADLINE_SEP = " +/by +";
    private static final String EVENT_SEP = " +/at +";

    private static final Pattern SAVE_PATTERN = Pattern.compile("^([TDE])([cx]) <<<< (.*) <<<< (.*)");

    static enum DateFormatEnum {
        T1("MMM d yyyy"),
        T2("dd/MM/yyyy"),
        T3("dd-MM-yyyy");

        final DateTimeFormatter dtf;

        DateFormatEnum(String signature) {
            dtf = DateTimeFormatter.ofPattern(signature);
        }
    }

    static enum DateTimeFormatEnum {
        T1("MMM d yyyy HH:mm"),
        T2("dd/MM/yyyy HH:mm"),
        T3("dd-MM-yyyy HH:mm");

        final DateTimeFormatter dtf;

        DateTimeFormatEnum(String signature) {
            dtf = DateTimeFormatter.ofPattern(signature);
        }
    }

    /**
     * Returns command corresponding to the info in ParsedData.
     * 
     * @param data Parsed information of the user input
     * @return Command To be executed and do actions.
     */
    public static Command dataToCommand(ParsedData data) {
        switch (data.command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(data);
            case "unmark":
                return new UnmarkCommand(data);
            case "delete":
                return new DeleteCommand(data);
            case "todo":
                return new TodoCommand(data);
            case "deadline":
                return new DeadlineCommand(data);
            case "event":
                return new EventCommand(data);
            case "by":
                return new ByCommand(data);
            case "find":
                return new FindCommand(data);
            default:
                return new InvalidCommand();
        }
    }

    /**
     * Returns ParsedData of a text based on the first space keyword
     * 
     * @param txt Raw user input
     * @return ParsedData Parsed user input
     */
    public static ParsedData parse(String txt) {
        String[] parsedTmp = txt.split(SPACE, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(parsedTmp[0]);
        }

        String command = parsedTmp[0];

        switch (command) {
            case "deadline":
                parsedTmp = parsedTmp[1].split(DEADLINE_SEP, 2);
                break;

            case "event":
                parsedTmp = parsedTmp[1].split(EVENT_SEP, 2);
                break;

            default:
                return new ParsedData(command, parsedTmp[1]);
        }
        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(command, parsedTmp[0]);
        }

        return new ParsedData(command, parsedTmp[0], parsedTmp[1]);
    }

    /**
     * Returns Command from raw user input.
     * Done by combining {@code Parser.parse} and
     * {@code Parser.dataToCommand}
     * 
     * @param txt Raw user input
     * @return Command
     */
    public static Command parseCommand(String txt) {
        return dataToCommand(parse(txt));
    }

    /**
     * Returns task based on one line of saved data.
     * 
     * @param savedLine One line in the save file
     * @return Task Corresponding task
     * @throws CorruptedLineException Throws when the line cannot be read
     */
    public static Task parseDataFromLine(String savedLine) throws CorruptedLineException {
        Matcher result = SAVE_PATTERN.matcher(savedLine);
        if (!result.find()) {
            throw new CorruptedLineException();
        }

        Task ret;
        switch (result.group(1)) {
            case "T":
                ret = Todo.createTodo(result.group(3));
                break;

            case "D":
                ret = Deadline.createDeadline(result.group(3), result.group(4));
                break;
            case "E":
                ret = Event.createEvent(result.group(3), result.group(4));
                break;
            default:
                throw new CorruptedLineException();
        }

        if (result.group(2).equals("c")) {
            ret.mark();
        } else if (!result.group(2).equals("x")) {
            throw new CorruptedLineException();
        }

        return ret;

    }

    /**
     * Converts regular String to a datetime format if its possible
     * 
     * @param str Target String
     * @return Optional<LocalDateTime> returns {@code Optional.empty} if invalid
     */
    public static Optional<LocalDateTime> strToDateTime(String str) {
        for (DateTimeFormatEnum signature : DateTimeFormatEnum.values()) {
            try {
                return Optional.of(LocalDateTime.parse(str, signature.dtf));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateFormatEnum signature : DateFormatEnum.values()) {
            try {
                return Optional.of(LocalDate.parse(str, signature.dtf).atStartOfDay());
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        return Optional.empty();
    }

}
