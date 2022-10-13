package duke.parser;

import duke.DukeException;
import duke.commands.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to extract information
 * from user input
 */
public class Parser {

    private static final String NUMBER_FORMAT_ERROR = "Please enter an integer";
    private static final  String MISSING_DESCRIPTION = "Invalid format! Missing description, please use the " +
            "following format:\n";
    private static final String INCORRECT_DATE_FORMAT = "Please enter the datetime in the following format:\n" +
            "<dd/mm/YYYY HHmm>";
    private static final String DEADLINE_FORMAT = "deadline <description> /by <dd/mm/YYYY HHmm>";
    private static final String EVENT_FORMAT = "event <description> /at <dd/mm/YYYY HHmm>";
    private static final String TODO_FORMAT = "todo <description>";
    private static final String FIND_ERROR = "Invalid format! Missing keyword!\n" +
            "Please use the following format:\nfind <keyword>";

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
        case "find":
            return parseFind(input);
        case "help":
            return new HelpCommand();
        default:
            return new UnknownCommand();
        }
    }

    private static Command parseMark(String[] input) {
        try {
            return new MarkCommand(Integer.parseInt(input[1].trim()) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_ERROR);
        }
    }

    private static Command parseUnmark(String[] input) {
        try {
            return new UnmarkCommand(Integer.parseInt(input[1].trim()) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_ERROR);
        }
    }

    private static Command parseDelete(String[] input) {
        try {
            return new DeleteCommand(Integer.parseInt(input[1].trim()) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException(NUMBER_FORMAT_ERROR);
        }
    }

    private static Command parseDeadline(String[] input) {
        try {
            String[] deadline = input[1].split(" /by ");
            LocalDateTime by = LocalDateTime.parse(deadline[1],
                    DateTimeFormatter.ofPattern("d/M/y HHmm"));
            String description = deadline[0];
            return new AddCommand(new Deadline(description, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MISSING_DESCRIPTION + DEADLINE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(INCORRECT_DATE_FORMAT);
        }
    }

    private static Command parseEvent(String[] input) {
        try {
            String[] event = input[1].split(" /at ");
            LocalDateTime at = LocalDateTime.parse(event[1],
                    DateTimeFormatter.ofPattern("d/M/y HHmm"));
            String description = event[0];
            return new AddCommand(new Event(description, at));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MISSING_DESCRIPTION + EVENT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(INCORRECT_DATE_FORMAT);
        }
    }

    private static Command parseToDo(String[] input) {
        try {
            String description = input[1].trim();
            if (description.equals("")) {
                throw new DukeException(MISSING_DESCRIPTION + TODO_FORMAT);
            }
            return new AddCommand(new ToDo(description));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MISSING_DESCRIPTION + TODO_FORMAT);
        }
    }

    private static Command parseFind(String[] input) {
        try {
            String search = input[1].trim();
            if (search.equals("")) {
                throw new DukeException(FIND_ERROR);
            }
            return new FindCommand(search);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(FIND_ERROR);
        }
    }
}