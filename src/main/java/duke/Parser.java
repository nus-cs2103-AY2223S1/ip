package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ScheduleCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTodoException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * The Parser class helps to make sense of user commands.
 *
 * @author Edric Yeo
 */
public class Parser {

    /**
     * Returns a Command given a user input.
     *
     * @param input The input entered by the user.
     * @return A Command that performs the task specified by the user.
     * @throws DukeException If input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        String[] words = input.split(" ");
        Command parsedCommand;
        switch (words[0]) {
        case "mark":
            int markIdx = Parser.parseIndex(input);
            parsedCommand = new MarkCommand(markIdx);
            break;
        case "unmark":
            int unmarkIdx = Parser.parseIndex(input);
            parsedCommand = new UnmarkCommand(unmarkIdx);
            break;
        case "todo":
            String todoDesc = getTodoDescription(input);
            parsedCommand = new AddCommand(new Todo(todoDesc));
            break;
        case "deadline":
            String[] formattedDeadlineFields = formatDeadline(input);
            parsedCommand = new AddCommand(new Deadline(formattedDeadlineFields[0], formattedDeadlineFields[1]));
            break;
        case "event":
            String[] formattedEventFields = formatEvent(input);
            parsedCommand = new AddCommand(new Event(formattedEventFields[0], formattedEventFields[1]));
            break;
        case "delete":
            int deleteIdx = Parser.parseIndex(input);
            parsedCommand = new DeleteCommand(deleteIdx);
            break;
        case "find":
            String keywordToFind = Parser.parseKeyword(input);
            parsedCommand = new FindCommand(keywordToFind);
            break;
        case "schedule":
            String scheduledDate = Parser.parseScheduledDate(input);
            parsedCommand = new ScheduleCommand(scheduledDate);
            break;
        default:
            throw new UnknownCommandException();
        }
        return parsedCommand;
    }

    /**
     * Returns the index of a task, given the user input.
     *
     * @param input The input entered by the user.
     * @return The index of the task.
     * @throws DukeException If the parsed index is invalid.
     */
    public static int parseIndex(String input) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length > 2) {
            throw new InvalidIndexException();
        }
        int idx = Integer.parseInt(splitInput[1]) - 1;
        assert idx >= 0;
        return idx;
    }

    /**
     * Returns the keyword to search for, given the user input.
     *
     * @param input The input entered by the user.
     * @return The keyword to search for.
     */
    public static String parseKeyword(String input) {
        String[] find = input.split("find ", 2);
        String keyword = find[1];
        return keyword;
    }

    /**
     * Returns formatted date field, given the user input.
     *
     * @param input The user input for a ScheduleCommand.
     * @return A String representing the formatted date.
     * @throws DukeException If input is invalid.
     */
    public static String parseScheduledDate(String input) throws DukeException {
        String[] schedule = input.split("schedule ", 2);
        String date = schedule[1];
        String formattedDate = formatDate(date);
        return formattedDate;
    }

    /**
     * Returns formatted date field, given the unformatted date.
     *
     * @param date The unformatted date.
     * @return A String representing the formatted date.
     * @throws DukeException If input is invalid.
     */
    public static String formatDate(String date) throws DukeException {
        String formattedDate = "";
        try {
            LocalDate unformattedDate = LocalDate.parse(date);
            formattedDate = unformattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
        return formattedDate;
    }

    /**
     * Returns the description field of a Todo, given the input.
     *
     * @param input The input entered by the user.
     * @return A String representing the description of the Todo.
     * @throws DukeException If input is invalid.
     */
    public static String getTodoDescription(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new EmptyTodoException();
        }
        return input.split("todo ", 2)[1];
    }

    /**
     * Returns a String array containing the description and date
     * of a Deadline, given the user input.
     *
     * @param input The input entered by the user.
     * @return A String array containing the description and date of the Deadline.
     * @throws DukeException If input is invalid.
     */
    public static String[] formatDeadline(String input) throws DukeException {
        String[] splitStringByBy = input.split(" /by ", 2);
        if (splitStringByBy.length <= 1) {
            throw new EmptyDateException();
        }
        String formattedDate = formatDate(splitStringByBy[1]);
        String desc = splitStringByBy[0].split("deadline ", 2)[1];
        return new String[]{desc, formattedDate};
    }

    /**
     * Returns a String array containing the description and date
     * of an Event, given the user input.
     *
     * @param input The input entered by the user.
     * @return A String array containing the description and date of the Event.
     * @throws DukeException If input is invalid.
     */
    public static String[] formatEvent(String input) throws DukeException {
        String[] splitStringByAt = input.split(" /at ", 2);
        if (splitStringByAt.length <= 1) {
            throw new EmptyDateException();
        }
        String formattedDate = formatDate(splitStringByAt[1]);
        String desc = splitStringByAt[0].split("event ", 2)[1];
        return new String[]{desc, formattedDate};
    }
}
