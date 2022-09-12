package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import duke.commands.SummaryCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.data.exception.DukeException;

/**
 * This class encapsulates the Parser which checks for
 * the validity of inputs provided
 */
public class Parser {
    private static final String INVALID_INPUT = "Invalid input!";
    private static final String INCORRECT_DATE_FORMAT = "Incorrect date format!";
    private static final String TODO_REGEX = "(?<desc>.+)";
    private static final String DEADLINE_REGEX = "(?<desc>.+)/by(?<date>.+)";
    private static final String EVENT_REGEX = "(?<desc>.+)/at(?<date>.+)";
    private static final String NUMBER_REGEX = "[0-9]+";

    /**
     * Makes sense of the commands provided by the user
     * @param input The input provided by the user
     * @return A Command to be executed
     * @throws DukeException If the input is invalid
     */
    public static Command parse(String input) throws DukeException {
        String[] components = input.split(" ", 2);
        String command = components[0];
        String args = components.length == 1 ? "" : components[1];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return prepareList(args);
        case "mark":
            return prepareMark(args);
        case "unmark":
            return prepareUnmark(args);
        case "todo":
            return prepareTodo(args);
        case "deadline":
            return prepareDeadline(args);
        case "event":
            return prepareEvent(args);
        case "delete":
            return prepareDelete(args);
        case "find":
            return prepareFind(args);
        case "summary":
            return new SummaryCommand();
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Checks if the date provided is valid
     * @param date The task's date
     */
    public static boolean isDateValid(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static ListCommand prepareList(String args) throws DukeException {
        if (args.equals("")) {
            return new ListCommand();
        }
        if (!isDateValid(args)) {
            throw new DukeException(Parser.INCORRECT_DATE_FORMAT);
        }

        return new ListCommand(args);
    }

    private static MarkCommand prepareMark(String args) throws DukeException {
        if (!args.matches(Parser.NUMBER_REGEX)) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        return new MarkCommand(Integer.parseInt(args));
    }

    private static UnmarkCommand prepareUnmark(String args) throws DukeException {
        if (!args.matches(Parser.NUMBER_REGEX)) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        return new UnmarkCommand(Integer.parseInt(args));
    }

    private static TodoCommand prepareTodo(String args) throws DukeException {
        Pattern pattern = Pattern.compile(Parser.TODO_REGEX);
        Matcher matcher = pattern.matcher(args);
        boolean hasMatches = matcher.matches();

        if (!hasMatches) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        return new TodoCommand(matcher.group("desc").trim());
    }

    private static DeadlineCommand prepareDeadline(String args) throws DukeException {
        Pattern pattern = Pattern.compile(Parser.DEADLINE_REGEX);
        Matcher matcher = pattern.matcher(args);
        boolean hasMatches = matcher.matches();

        if (!hasMatches) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        if (!isDateValid(matcher.group("date").trim())) {
            throw new DukeException(Parser.INCORRECT_DATE_FORMAT);
        }

        return new DeadlineCommand(matcher.group("desc").trim(), matcher.group("date").trim());
    }

    private static EventCommand prepareEvent(String args) throws DukeException {
        Pattern pattern = Pattern.compile(Parser.EVENT_REGEX);
        Matcher matcher = pattern.matcher(args);
        boolean hasMatches = matcher.matches();

        if (!hasMatches) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        if (!isDateValid(matcher.group("date").trim())) {
            throw new DukeException(Parser.INCORRECT_DATE_FORMAT);
        }

        return new EventCommand(matcher.group("desc").trim(), matcher.group("date").trim());
    }

    private static DeleteCommand prepareDelete(String args) throws DukeException {
        if (!args.matches(Parser.NUMBER_REGEX)) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        return new DeleteCommand(Integer.parseInt(args));
    }

    private static FindCommand prepareFind(String args) throws DukeException {
        if (args.matches("")) {
            throw new DukeException(Parser.INVALID_INPUT);
        }

        return new FindCommand(args.split(" "));
    }
}
