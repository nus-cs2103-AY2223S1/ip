package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Parser {
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
                return new DeadlineCommand(
                        Parser.concatenateArguments(arguments, 1, delimiter),
                        Parser.concatenateArguments(arguments, delimiter + 1)
                );
            case "event":
                if (arguments.length < 3) {
                    throw new DukeException("Missing event description and/or date-time");
                }
                delimiter = Parser.findArgumentIndex(arguments, "/at");
                return new EventCommand(
                        Parser.concatenateArguments(arguments, 1, delimiter),
                        Parser.concatenateArguments(arguments, delimiter + 1)
                );
            case "list":
                return new ListCommand();
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
            default: {
                throw new DukeException("Unknown command");
            }
        }
    }

    private static String[] getArguments(String command) {
        if (command.isBlank()) {
            return new String[0];
        }
        return command.split(" ");
    }

    private static int findArgumentIndex(String[] arguments, String query) {
        return IntStream.range(0, arguments.length)
                .filter(i -> arguments[i].equals(query))
                .findFirst()
                .orElseThrow(() -> new DukeException(String.format(
                        "Missing argument `%s`",
                        query
                )));
    }

    private static String concatenateArguments(String[] arguments, int start, int end) {
        return Arrays.stream(arguments, start, end)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static String concatenateArguments(String[] arguments, int start) {
        return Arrays.stream(arguments, start, arguments.length)
                .map(str -> str + " ")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString()
                .strip();
    }
}