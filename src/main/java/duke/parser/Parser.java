package duke.parser;

import duke.DukeException;
import duke.commands.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser to extract information
 * from user input
 */
public class Parser {

    /**
     * Parses the user input into various commands
     *
     * @param command a string representing the user input
     * @return A command corresponding to the user input
     */
    public static Command parse(String command) {
        String[] input = command.split(" ", 2);

        String commandType = input[0].toLowerCase();

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMark(input);
        case "unmark":
            return parseUnmark(input);
        case "deadline":
            return parseDeadline(input);
        case "event":
            return parseEvent(input);
        case "todo":
            return parseToDo(input);
        case "delete":
            return parseDelete(input);
        default:
            return new UnknownCommand();
        }
    }

    private static Command parseMark(String[] input) {
        return new MarkCommand(Integer.parseInt(input[1].trim()) - 1);
    }

    private static Command parseUnmark(String[] input) {
        return new UnmarkCommand(Integer.parseInt(input[1].trim()) - 1);
    }

    private static Command parseDelete(String[] input) {
        return new DeleteCommand(Integer.parseInt(input[1].trim()) - 1);
    }

    private static Command parseDeadline(String[] input) {
        String[] deadline = input[1].split(" /by ");
        LocalDateTime by = LocalDateTime.parse(deadline[1],
                DateTimeFormatter.ofPattern("d/M/y HHmm"));
        String description = deadline[0];
        return new AddCommand(new Deadline(description, by));
    }

    private static Command parseEvent(String[] input) {
        String[] event = input[1].split(" /by ");
        LocalDateTime at = LocalDateTime.parse(event[1],
                DateTimeFormatter.ofPattern("d/M/y HHmm"));
        String description = event[0];
        return new AddCommand(new Deadline(description, at));
    }

    private static Command parseToDo(String[] input) {
        String description = input[1];
        return new AddCommand(new ToDo(description));
    }

    private static Command parseFind(String[] input) {
        String search = input[1];
        return new FindCommand(search);
    }
}