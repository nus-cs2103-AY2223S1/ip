package chatbot.parser;

import chatbot.commands.*;
import chatbot.exceptions.DukeException;
import chatbot.tasks.Deadline;
import chatbot.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class is responsible for parsing user input and determining the consequent action.
 */
public class Parser {
    public static final Pattern EVENT_FORMAT = Pattern.compile("(?<taskName>.+)\\s+/at\\s+(?<date>\\S+)");
    public static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<taskName>.+)\\s+/by\\s+(?<date>\\S+)");
    public static final Pattern TASK_INDEX_FORMAT = Pattern.compile("(?<taskIndex>\\d+)");
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * The method parses the user input and determine the command that should be executed accordingly.
     *
     * @param userInput The user input to be parsed.
     * @return The command to be executed
     * @throws DukeException Raised shall the user input has format related error.
     */
    public static Command parse(String userInput) throws DukeException {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw DukeException.INVALID_COMMAND;
        }

        String command = matcher.group("command");
        String args = matcher.group("arguments").trim();

        switch (command) {
        case "list":
            LocalDate date = parseList(args);
            if (date == null) {
                return List.LIST;
            } else {
                return new ListOn(date);
            }
        case "delete":
            return new Delete(parseTargetIndex(args));
        case "mark":
            return new Mark(parseTargetIndex(args));
        case "unmark":
            return new Unmark(parseTargetIndex(args));
        case "todo":
            return new AddTodo(parseTodo(args));
        case "event":
            String[] eventSpecs = parseTimedTask(Event.TYPE, args);
            LocalDate eventDate;
            try {
                eventDate = LocalDate.parse(eventSpecs[1]);
            } catch (DateTimeParseException e) {
                throw DukeException.INVALID_DATE_FORMAT;
            }

            return new AddEvent(eventSpecs[0], eventDate);
        case "deadline":
            String[] deadlineSpecs = parseTimedTask(Deadline.TYPE, args);
            LocalDate deadline;
            try {
                deadline = LocalDate.parse(deadlineSpecs[1]);
            } catch (DateTimeParseException e) {
                throw DukeException.INVALID_DATE_FORMAT;
            }

            return new AddDeadline(deadlineSpecs[0], deadline);
        case "bye":
            return Exit.EXIT;
        default:
            throw DukeException.INVALID_COMMAND;
        }
    }

    /**
     * The method parses the argument segment of the user input to look for an integer.
     *
     * @param args The argument to be parsed.
     * @return The index of the task to be operated on.
     * @throws DukeException Raised if the argument does not contain a valid index.
     */
    public static int parseTargetIndex(String args) throws DukeException {
        Matcher indexMatcher = TASK_INDEX_FORMAT.matcher(args);
        if (!indexMatcher.matches()) {
            throw DukeException.TASK_INDEX_MISSING;
        }

        String index = indexMatcher.group("taskIndex");
        if (index.length() != args.length()) {
            throw DukeException.INVALID_INDEX_FORMAT;
        }

        return Integer.parseInt(index);
    }

    /**
     * The method parses the argument segment of the user input
     * to look for the task name of a task.
     *
     * @param args The argument to be parsed.
     * @return The task name.
     * @throws DukeException Raised if the argument does not contain
     * a task name.
     */
    public static String parseTodo(String args) throws DukeException {
        String taskName = args.trim();
        if (taskName.isEmpty()) {
            throw DukeException.INSUFFICIENT_TASK_SPECIFICATION;
        }

        return taskName;
    }

    /**
     * The method parses the argument segment of the user input
     * to look for the task name and date of a time sensitive task.
     *
     * @param type The type of time sensitive task.
     * @param args The argument to be parsed.
     * @return An array containing the name and date of the task.
     * @throws DukeException Raised if the argument does not contain
     * a task name and a date.
     */
    public static String[] parseTimedTask(String type, String args) throws DukeException {
        Matcher matcher;
        if (type == Deadline.TYPE) {
             matcher = DEADLINE_FORMAT.matcher(args);
        } else {
            matcher = EVENT_FORMAT.matcher(args);
        }

        if (!matcher.matches()) {
            throw DukeException.INSUFFICIENT_TASK_SPECIFICATION;
        }

        String taskName = matcher.group("taskName").trim();
        String date = matcher.group("date");


        return new String[] {taskName, date};
    }

    public static LocalDate parseList(String args) throws DukeException {
        String trimmed = args.trim();
        if (trimmed.isEmpty()) {
            return null;
        } else {
            try {
                return LocalDate.parse(trimmed);
            } catch (DateTimeParseException e) {
                throw DukeException.INVALID_DATE_FORMAT;
            }
        }
    }
}
