package common;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.GoodbyeCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.StatisticsCommand;
import commands.ToDoCommand;
import commands.UnmarkCommand;
import dukeexceptions.DukeException;
import dukeexceptions.UnknownCommandException;

enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    STATISTICS,
}

/**
 * Parser class parses user input and provides static utility functions to parse miscellaneous strings.
 */
public class Parser {

    /**
     * Parses user input obtained from Scanner, and returns a Command that depends on the user input.
     * Certain commands will trigger validation to ensure that the command arguments are valid.
     *
     * @param fullCommand Unparsed user input obtained from scanner.
     * @return Command object to be executed later.
     * @throws DukeException Occurs when an error occurs during parsing, such as invalid arguments.
     */
    public static commands.Command parseInput(String fullCommand) throws DukeException {
        String[] userInputs = fullCommand.split(" ");
        String unparsedCommand = userInputs[0];
        String[] args = Arrays.copyOfRange(userInputs, 1, userInputs.length);

        // Check if command is valid
        if (!isValidCommandEnum(unparsedCommand.toUpperCase())) {
            throw new UnknownCommandException();
        }

        Command command = Command.valueOf(userInputs[0].toUpperCase());

        switch (command) {
        case BYE: {
            return new GoodbyeCommand();
        }
        case LIST: {
            return new ListCommand();
        }
        case MARK: {
            MarkCommand.validateArguments(args);
            return new MarkCommand(args);
        }
        case UNMARK: {
            UnmarkCommand.validateArguments(args);
            return new UnmarkCommand(args);
        }
        case TODO: {
            ToDoCommand.validateArguments(args);
            return new ToDoCommand(args);
        }
        case DEADLINE: {
            DeadlineCommand.validateArguments(args);
            return new DeadlineCommand(args);
        }
        case EVENT: {
            EventCommand.validateArguments(args);
            return new EventCommand(args);
        }
        case DELETE: {
            return new DeleteCommand(args);
        }
        case FIND: {
            FindCommand.validateArguments(args);
            return new FindCommand(args);
        }
        case STATISTICS: {
            StatisticsCommand.validateArguments(args);
            return new StatisticsCommand(args);
        }
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Given a string, return true if it is a valid common.COMMANDS enum.
     *
     * @param str String to check if the enum exists.
     * @return True if the provided string is a valid common.COMMANDS enum.
     */
    public static boolean isValidCommandEnum(String str) {
        for (Command cmd : Command.values()) {
            if (str.equalsIgnoreCase(cmd.name())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given a string and a DateTime format, validate if the given string
     * follows the DateTime format.
     *
     * @param str    The string to be validated against.
     * @param format The format the string should follow.
     * @return Boolean representing if str follows the specified format.
     */
    public static boolean isValidDatetime(String str, String format) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
            dtf.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Splits an array into subarrays at a given delimiter, and concatenates the substrings.
     * For example, given ['a', 'b', '\n', 'c', 'd'], with the delimiter specified to be '\n',
     * the function splits the array at '\n' and concatenates the split subarrays to return
     * ['ab', 'cd']
     *
     * @param arr       Array of strings to be split
     * @param delimiter Array is split into two subarrays at the delimiter,
     *                  and each subarray's elements are concatenated with a " " between each element
     * @return An array of two substrings
     */
    public static List<String> splitArrayIntoSubstrings(String[] arr, String delimiter) {
        StringBuilder builder = new StringBuilder();
        List<String> res = new ArrayList<>();

        for (String s : arr) {
            if (s.equalsIgnoreCase(delimiter)) {
                res.add(builder.toString().trim());
                builder = new StringBuilder();
            } else {
                builder.append(s).append(" ");
            }
        }
        res.add(builder.toString().trim());
        return res;
    }

    /**
     * Splits a string according to a given delimiter, and returns the
     * string at the specified index.
     *
     * @param target    String to split.
     * @param delimiter Delimiter to split string at.
     * @param idx       Index to return after string split.
     * @return Substring specified by the index after string is split.
     */
    public static String splitString(String target, String delimiter, Integer idx) {
        assert !idx.equals(null) : "No parameter provided";
        return target.split(delimiter)[idx].strip();
    }
}
