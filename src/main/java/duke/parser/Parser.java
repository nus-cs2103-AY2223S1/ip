package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.UnmarkCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * Represents a class that can extract commands and tasks from user input.
 */
public class Parser {
    /**
     * Returns an executable command object by parsing user input.
     *
     * @param input User input from app's text field.
     * @return Executable command object.
     * @throws DukeException If user input is invalid.
     */
    public static Command getCommand(String input) throws DukeException {
        String[] commandTokens = input.split("\\s+", 2);
        String direction = commandTokens[0];
        String meta = commandTokens.length > 1 ? commandTokens[1] : null;
        switch (direction) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "unmark":
                int unmarkIndex = extractIndex(meta);
                return new UnmarkCommand(unmarkIndex);
            case "mark":
                int markIndex = extractIndex(meta);
                return new MarkCommand(markIndex);
            case "delete":
                int deleteIndex = extractIndex(meta);
                return new DeleteCommand(deleteIndex);
            case "deadline":
                String[] deadlineTokens = getTaskTokens(meta, Message.NO_BY_ERROR);
                return new AddDeadlineCommand(deadlineTokens[0], parseDateTime(deadlineTokens[1]));
            case "event":
                String[] eventTokens = getTaskTokens(meta, Message.NO_AT_ERROR);
                return new AddEventCommand(eventTokens[0], parseDateTime(eventTokens[1]));
            case "todo":
                validateMetaNullity(meta);
                return new AddTodoCommand(meta);
            case "find":
                validateMetaNullity(meta);
                return new FindCommand(meta);
            default:
                throw new DukeException(Message.INVALID);
        }
    }

    /**
     * Returns a task object by parsing user input.
     *
     * @param input User input from app's text field.
     * @return Task object.
     * @throws DukeException If user input is invalid.
     */
    public static Task getTask(String input) throws DukeException {
        String[] commandTokens = input.split("\\s+", 2);
        String direction = commandTokens[0];
        String meta = commandTokens.length > 1 ? commandTokens[1] : null;
        switch (direction) {
            case "deadline":
                String[] deadlineTokens = getTaskTokens(meta, Message.NO_BY_ERROR);
                return new Deadline(deadlineTokens[0], parseDateTime(deadlineTokens[1]));
            case "event":
                String[] eventTokens = getTaskTokens(meta, Message.NO_AT_ERROR);
                return new Event(eventTokens[0], parseDateTime(eventTokens[1]));
            case "todo":
                validateMetaNullity(meta);
                return new Todo(meta);
            default:
                throw new DukeException(Message.INVALID);
        }
    }

    /**
     * Throws an exception if the meta is null.
     *
     * @param meta The part of the user input that contains description, date etc.
     * @throws DukeException If meta is null.
     */
    private static void validateMetaNullity(String meta) throws DukeException {
        if (meta == null) {
            throw new DukeException(Message.DESCRIPTION_EMPTY);
        }
    }

    /**
     * Throws an exception if the tokens are not expected size.
     *
     * @param tokens Parsed task tokens.
     * @param invalidMsg Exception message to use.
     * @throws DukeException If tokens length < 2.
     */
    private static void validateTokens(String[] tokens, Message invalidMsg) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException(invalidMsg);
        }
    }

    /**
     * Returns parsed task tokens.
     *
     * @param meta The part of the user input that contains description, date etc.
     * @param invalidMsg Exception message to use.
     * @throws DukeException If meta is null or tokens are invalid.
     */
    private static String[] getTaskTokens(String meta, Message invalidMsg) throws DukeException {
        validateMetaNullity(meta);
        String[] tokens = meta.split("\\s*/by\\s*", 2);
        validateTokens(tokens, invalidMsg);
        return tokens;
    }

    /**
     * Returns date-time object from user input.
     * @param timeString User inputted time string.
     * @return Parsed date-time object from user input.
     * @throws DukeException If user input is invalid.
     */
    private static LocalDateTime parseDateTime(String timeString) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy[ H:mm]");
            TemporalAccessor temporalAccessor = formatter.parseBest(
                    timeString,
                    LocalDateTime::from,
                    LocalDate::from
            );
            if (temporalAccessor instanceof LocalDateTime) {
                return (LocalDateTime) temporalAccessor;
            } else {
                return ((LocalDate) temporalAccessor).atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(Message.DATE_FORMAT_ERROR);
        }
    }

    /**
     * Returns an integer parsed from the meta.
     *
     * @param meta User inputted integer value.
     * @return Parsed integer from meta.
     * @throws DukeException If user input cannot be cast to integer.
     */
    private static int extractIndex(String meta) throws DukeException {
        validateMetaNullity(meta);
        try {
            return Integer.parseInt(meta.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new DukeException(Message.TASK_INDEX_ERROR);
        }
    }

}