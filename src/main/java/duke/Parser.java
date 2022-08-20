package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A parser object can parse a command from a string,
 * and determine which Command object should be used to execute it.
 */
public class Parser {
    /**
     * Parses a command from a string.
     * 
     * @param str the string to parse
     * @return the Command object to execute
     */
    public static Command parse(String str) {
        String[] arguments = Parser.getArguments(str);
        if (arguments.length == 0) {
            throw new DukeException("Missing command");
        }
        switch (arguments[0]) {
        case "bye":
            return new ByeCommand();
        case "todo":
            if (arguments.length < 2) {
                throw new DukeException("Missing todo description");
            }
            return new TodoCommand(Parser.concatenateArguments(arguments, 1));
        case "deadline":
            if (arguments.length < 3) {
                throw new DukeException("Missing deadline description and/or deadline");
            }
            // Find the "/by" delimiter to get the two arguments.
            int delimiter = Parser.findArgumentIndex(arguments, "/by");
            return new DeadlineCommand(Parser.concatenateArguments(arguments, 1, delimiter),
                    Parser.concatenateArguments(arguments, delimiter + 1)
            );
        case "event":
            if (arguments.length < 3) {
                throw new DukeException("Missing event description and/or date-time");
            }
            delimiter = Parser.findArgumentIndex(arguments, "/at");
            return new EventCommand(Parser.concatenateArguments(arguments, 1, delimiter),
                    Parser.concatenateArguments(arguments, delimiter + 1)
            );
        case "list":
            return new ListCommand();
        case "find":
            if (arguments.length < 2) {
                throw new DukeException("Missing search query");
            }
            return new FindCommand(Parser.concatenateArguments(arguments, 1));
        case "mark":
            if (arguments.length < 2) {
                throw new DukeException("Missing index");
            }
            return new MarkCommand(Integer.parseInt(arguments[1]) - 1); // 1-indexed
        case "unmark":
            if (arguments.length < 2) {
                throw new DukeException("Missing index");
            }
            return new UnmarkCommand(Integer.parseInt(arguments[1]) - 1); // 1-indexed
        case "delete":
            if (arguments.length < 2) {
                throw new DukeException("Missing index");
            }
            return new DeleteCommand(Integer.parseInt(arguments[1]) - 1);
        default:
            throw new DukeException("Unknown command");
        }
    }

    /**
     * Splits a string into an array of arguments, delimited by spaces.
     * 
     * @param command the string to split
     * @return the array of arguments
     */
    private static String[] getArguments(String command) {
        if (command.isBlank()) {
            return new String[0];
        }
        return command.split(" ");
    }

    /**
     * Returns the index of the first occurrence of a search string in an array of arguments.
     * 
     * @param arguments the array of arguments
     * @param query the search string
     * @return the index of the first occurrence of the search string
     * @throws DukeException if the search string is not found
     */
    private static int findArgumentIndex(String[] arguments, String query) {
        return IntStream.range(0, arguments.length)
                .filter(i -> arguments[i].equals(query))
                .findFirst()
                .orElseThrow(() -> new DukeException(String.format("Missing argument `%s`",
                        query
                )));
    }

    /**
     * Joins an array of arguments into a single string, with spaces between them.
     * 
     * @param arguments the array of arguments
     * @param start the index of the first argument (inclusive)
     * @param end the end index of the last argument (exclusive)
     * @return the joined string
     */
    private static String concatenateArguments(String[] arguments, int start, int end) {
        return Arrays.stream(arguments, start, end)
                .map(str -> str + " ")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * Joins an array of arguments into a single string, with spaces between them.
     * Overloaded variant which concatenates all arguments from the start index,
     * to the end of the array.
     * 
     * @param arguments the array of arguments
     * @param start the index of the first argument (inclusive)
     * @return the joined string
     */
    private static String concatenateArguments(String[] arguments, int start) {
        return Arrays.stream(arguments, start, arguments.length)
                .map(str -> str + " ")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .strip();
    }
}