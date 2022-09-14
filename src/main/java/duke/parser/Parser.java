package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.ParseInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Represents a parser that interprets the user input and
 * turns the input into a command understood by duke.
 */
public class Parser {

    private static final List<String> formatStrings = Arrays.asList(
            "yyyy/MM/dd", "yyyy-MM-dd", "MMM dd yyyy");

    /**
     * Parses the given user input and returns a command.
     * If the user input cannot be recognised as a valid command,
     * a DukeException is thrown.
     *
     * @param fullCommand User input.
     * @return Command.
     * @throws ParseInputException If the user input is not a valid command.
     */
    public static Command parse(String fullCommand) throws ParseInputException {

        String commandWord;
        String description;
        if (fullCommand.contains(" ")) {
            String[] arr = fullCommand.split(" ", 2);
            commandWord = arr[0];
            description = arr[1];
        } else {
            commandWord = fullCommand;
            description = null;
        }

        switch (commandWord) {
        case DeadlineCommand.COMMAND_WORD:
            // Fallthrough
        case EventCommand.COMMAND_WORD:
            // Fallthrough
        case ToDoCommand.COMMAND_WORD:
            return prepareAdd(commandWord, description);
        case DeleteCommand.COMMAND_WORD:
            // Fallthrough
        case MarkCommand.COMMAND_WORD:
            // Fallthrough
        case UnmarkCommand.COMMAND_WORD:
            return parseIndex(commandWord, description);
        case DateCommand.COMMAND_WORD:
            return new DateCommand(description);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommand(description);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        default:
            String errorMessage = "☹ You are so dumb that you don't even know how to input a simple command!";
            throw new ParseInputException(errorMessage);
        }
    }

    /**
     * Processes the task to be added and specifies which type of Task it is,
     * and returns an AddCommand instance.
     * It throws a DukeException if the description of the task is null.
     *
     * @param commandWord Either "deadline" or "event" or "todo".
     * @param description Description of the task.
     * @return AddCommand.
     * @throws ParseInputException If the description of a task is empty.
     */
    public static Command prepareAdd(String commandWord, String description) throws ParseInputException {
        if (description == null) {
            throw new ParseInputException("☹ Are you stupid? Description of a task cannot be empty!");
        } else if (commandWord.equals(ToDoCommand.COMMAND_WORD)) {
            return new ToDoCommand(new ToDo(description));
        } else {
            return parseDate(commandWord, description);
        }
    }

    /**
     * Parses an input that potentially includes a date,
     * and returns an AddCommand instance.
     * It throws a DukeException when the input does not contain the required keyword.
     *
     * @param commandWord Either "deadline" or "event".
     * @param description Description of the task.
     * @return AddCommand.
     * @throws ParseInputException If the required keyword (e.g. "/by" or "/at") is missing.
     */
    public static Command parseDate(String commandWord, String description) throws ParseInputException {
        switch (commandWord) {
        case DeadlineCommand.COMMAND_WORD:
            if (description.contains(" /by ")) {
                String[] arr = description.split(" /by ", 2);
                String message = arr[0];
                LocalDate date = parseDateFormats(arr[1]);
                return new DeadlineCommand(new Deadline(message, date));
            } else {
                String errorMessage = "☹ Go read the user guide and "
                        + "follow the format <deadline description /by date>";
                throw new ParseInputException(errorMessage);
            }
        case EventCommand.COMMAND_WORD:
            if (description.contains(" /at ")) {
                String[] arr = description.split(" /at ", 2);
                String message = arr[0];
                LocalDate date = parseDateFormats(arr[1]);
                return new EventCommand(new Event(message, date));
            } else {
                String errorMessage = "☹ Go read the user guide and "
                        + "follow the format <event description /at date>";
                throw new ParseInputException(errorMessage);
            }
        default:
            String errorMessage = "☹ Wrong type of command detected!";
            throw new ParseInputException(errorMessage);
        }
    }

    /**
     * Parses the given date using preset formats
     * Returns a LocalDate instance.
     * If the given date does not fulfil one of the preset formats,
     * a DukeException is thrown.
     *
     * @param dateString Date as string.
     * @return LocalDate instance.
     * @throws ParseInputException If the given date string is not in one of the preset formats.
     */
    public static LocalDate parseDateFormats(String dateString) throws ParseInputException {
        // @@author Wu-Lezheng-reused
        // Reused from
        // https://stackoverflow.com/questions/4024544/how-to-parse-dates-in-multiple-formats-using-simpledateformat
        // with minor modifications
        for (String formatString : formatStrings) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
                Date date = dateFormat.parse(dateString);
                SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
                String result = newFormat.format(date);
                return LocalDate.parse(result);
            } catch (ParseException pe) {
                // Let the loop continue
            }
        }
        throw new ParseInputException("☹ Invalid date format!\nFollow yyyy/MM/dd or yyyy-MM-dd");
    }

    /**
     * Parses an input that potentially includes an index.
     * Returns a command that is either a DeleteCommand, MarkCommand, or UnmarkCommand.
     *
     * @param commandWord Either "delete" or "mark" or "unmark".
     * @param description Description of the command.
     * @return A command.
     * @throws ParseInputException If the given index is invalid.
     */
    public static Command parseIndex(String commandWord, String description) throws ParseInputException {
        try {
            int index = Integer.parseInt(description) - 1;
            switch (commandWord) {
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(index);
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(index);
            default:
                return new UnmarkCommand(index);
            }
        } catch (NumberFormatException nfe) {
            String errorMessage = "☹ Enter an index of a task!";
            throw new ParseInputException(errorMessage);
        }
    }

}
