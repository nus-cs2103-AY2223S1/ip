package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.exception.UnknownCommand;

/**
 * A class that deals with making sense of user commands.
 */
public class Parser {
    private enum Commands { list, mark, unmark, todo, event, deadline, delete, bye, find, help }

    /**
     * Takes in the user input, process it and returns the
     * associated command.
     *
     * @param input The string the user inputted.
     * @return The command associated with the user input.
     * @throws DukeException If user input is invalid.
     */
    public Command parse(String input) throws DukeException {
        Commands command;
        try {
            command = Commands.valueOf(input.split(" ")[0]);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommand();
        }
        switch (command) {
        case list:
            return new ListCommand();
        case mark:
            String indexToMarkString = getContent(input, "mark");
            return new MarkCommand(indexToMarkString, true);
        case unmark:
            String indexToUnmarkString = getContent(input, "unmark");
            return new MarkCommand(indexToUnmarkString, false);
        case todo:
            String todoDescription = getContent(input, "todo");
            getDescription(todoDescription, true);
            return new AddCommand("T", todoDescription, null);
        case event:
            String[] eventDetails = getContent(input, "event").split("/at");
            checkFormat(eventDetails);
            String eventDescription = getDescription(eventDetails[0].strip(), true);
            LocalDate eventDate = getDate(eventDetails[1].strip());
            return new AddCommand("E", eventDescription, eventDate);
        case deadline:
            String[] deadlineDetails = getContent(input, "deadline").split("/by");
            checkFormat(deadlineDetails);
            String deadlineDescription = getDescription(deadlineDetails[0].strip(), true);
            LocalDate deadlineDate = getDate(deadlineDetails[1].strip());
            return new AddCommand("D", deadlineDescription, deadlineDate);
        case delete:
            String indexString = getContent(input, "delete");
            return new DeleteCommand(indexString);
        case find:
            String query = getContent(input, "find");
            getDescription(query, false);
            return new FindCommand(query);
        case help:
            return new HelpCommand();
        case bye:
            return new ExitCommand();
        default:
            throw new UnknownCommand();
        }
    }

    /**
     * Extracts out the content of the command from the
     * whole user input.
     *
     * @param input The user input.
     * @param command The command the user specified.
     * @return The content of the command.
     */
    private String getContent(String input, String command) {
        return input.substring(command.length()).trim();
    }

    /**
     * Checks the length of the description or query and returns
     * the description.
     *
     * @param str The string to check the length of.
     * @param isDescription If the string provided is a description.
     * @return The description.
     * @throws InvalidInput If the length of the string is 0.
     */
    private String getDescription(String str, boolean isDescription) throws InvalidInput {
        if (str.length() == 0) {
            if (isDescription) {
                throw new InvalidInput("The description cannot be empty.");
            } else {
                throw new InvalidInput("Query cannot be empty.");
            }
        }
        return str;
    }

    /**
     * Checks if the format of the details provided is valid.
     *
     * @param details An array of string containing details of the task.
     * @throws InvalidInput If the length of the array is not 2.
     */
    private void checkFormat(String[] details) throws InvalidInput {
        if (details.length != 2) {
            throw new InvalidInput("Ensure input format is correct.");
        }
    }

    /**
     * Returns the LocalDate from a given string.
     *
     * @param date The string containing the date.
     * @return The date in LocalDate.
     * @throws InvalidInput If the format of the date string is invalid.
     */
    private LocalDate getDate(String date) throws InvalidInput {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidInput("Date format should be yyyy-mm-dd");
        }
    }
}
