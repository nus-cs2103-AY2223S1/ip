package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateException;
import duke.exception.UnknownCommandException;

/**
 * A class to make sense of user inputs.
 *
 * @author Elbert Benedict
 */
public class Parser {
    /**
     * Converts a date string to a LocalDate instance.
     *
     * @param string the date string.
     * @return The LocalDate instance representing the date string.
     * @throws DukeException If the string is not a valid date.
     */
    public static LocalDate convertToDateObj(String string) throws DukeException {
        try {
            return LocalDate.parse(string);
        } catch (DateTimeException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns the string representation of a Localdate instance.
     *
     * @param date the Localdate instance.
     * @return string representation of a Localdate instance.
     */
    public static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts a Localdate instance to save file string
     * representation.
     *
     * @param date the Localdate instance.
     * @return the date save file string representation.
     */
    public static String printSaveFileDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Parses user input to the corresponding command.
     *
     * @param input the user input.
     * @return Command to be executed.
     * @throws DukeException If input is not valid.
     */
    public static Command parseUserInput(String input) throws DukeException {
        //remove leading and trailing whitespace
        String trimmed = input.trim();
        //Split by regex to get command
        String[] splitted = trimmed.split("\\s", 2);
        String command = splitted[0];
        String description = splitted.length < 2 ? "" : splitted[1].trim();

        switch (command) {
        case TodoCommand.COMMAND:
            return new TodoCommand(description);

        case DeadlineCommand.COMMAND:
            return new DeadlineCommand(description);

        case EventCommand.COMMAND:
            return new EventCommand(description);

        case MarkCommand.COMMAND:
            return new MarkCommand(description);

        case UnmarkCommand.COMMAND:
            return new UnmarkCommand(description);

        case DeleteCommand.COMMAND:
            return new DeleteCommand(description);

        case PriorityCommand.COMMAND:
            return new PriorityCommand(description);

        case FindCommand.COMMAND:
            return new FindCommand(description);

        case ByeCommand.COMMAND:
            return new ByeCommand();

        case ListCommand.COMMAND:
            return new ListCommand();

        default:
            throw new UnknownCommandException();
        }
    }
}
