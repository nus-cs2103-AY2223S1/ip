package ado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import ado.command.ByeCommand;
import ado.command.Command;
import ado.command.DeadlineCommand;
import ado.command.DeleteCommand;
import ado.command.EventCommand;
import ado.command.FindCommand;
import ado.command.HelpCommand;
import ado.command.ListCommand;
import ado.command.MarkCommand;
import ado.command.TodoCommand;
import ado.command.UnmarkCommand;

/**
 * Parses the user input and executes the corresponding commands.
 */
public class Parser {
    /**
     * Returns Command object based on user's input.
     * @param fullCommand user's input.
     * @return specific command.
     * @throws AdoException If command does not exist.
     */
    public static Command parse(String fullCommand) throws AdoException {
        if (fullCommand.isEmpty()) {
            throw new AdoException("type something :/");
        }
        String[] inputSegments = fullCommand.split(" ", 2);
        String commandFirstWord = inputSegments[0].toLowerCase().trim();
        String commandDetails = (inputSegments.length <= 1) ? "" : inputSegments[1];

        switch (commandFirstWord) {
        case "list":
            validateSingleCommand(commandDetails);
            return new ListCommand();
        case "bye":
            validateSingleCommand(commandDetails);
            return new ByeCommand();
        case "help":
            validateSingleCommand(commandDetails);
            return new HelpCommand();
        case "todo":
            return validateTodoCommand(commandDetails);
        case "deadline":
            return validateDeadlineCommand(commandDetails);
        case "event":
            return validateEventCommand(fullCommand);
        case "mark":
            validateMultipleCommand(fullCommand);
            assert (inputSegments[1] != null) : "Missing index";
            return new MarkCommand(Integer.parseInt(inputSegments[1].trim()));
        case "unmark":
            validateMultipleCommand(fullCommand);
            assert (inputSegments[1] != null) : "Missing index";
            return new UnmarkCommand(Integer.parseInt(inputSegments[1].trim()));
        case "delete":
            validateMultipleCommand(fullCommand);
            assert (inputSegments[1] != null) : "Missing index";
            return new DeleteCommand(Integer.parseInt(inputSegments[1].trim()));
        case "find":
            validateMultipleCommand(fullCommand);
            assert (inputSegments[1] != null) : "Missing find keyword";
            return new FindCommand(inputSegments[1].trim());
        default:
            throw new AdoException(commandFirstWord + Constants.INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Checks if user input only consists of main command.
     * @param commandDetails string of user's input after the first word.
     * @throws AdoException If input has multiple words in input.
     */
    public static void validateSingleCommand(String commandDetails) throws AdoException {
        boolean inputIsMultipleWords = !commandDetails.trim().isEmpty() ? true : false;
        if (inputIsMultipleWords) {
            throw new AdoException(Constants.INVALID_SINGLE_COMMAND_MESSAGE);
        }
    }

    /**
     * Checks if user input has a valid description for creation of tasks.
     * @param commandDetails string of user's input after the first word.
     * @throws AdoException If input has only word in input.
     */
    public static void validateMultipleCommand(String commandDetails) throws AdoException {
        boolean inputIsSingleWord = commandDetails.trim().isEmpty() ? true : false;
        if (inputIsSingleWord) {
            throw new AdoException(Constants.MISSING_DESCRIPTION_MESSAGE);
        }
    }

    /**
     * Checks if user input is valid for todo commands.
     * @param commandDetails string of user's input after the first word.
     * @return TodoCommand with valid inputs.
     * @throws AdoException If task description is empty.
     */
    public static TodoCommand validateTodoCommand(String commandDetails) throws AdoException {
        //checks if input has text after first word
        validateMultipleCommand(commandDetails);
        String taskName = commandDetails.trim();
        return new TodoCommand(taskName);
    }

    /**
     * Checks if user input is valid for deadline commands.
     * @param commandDetails string of user's input after the first word.
     * @return DeadlineCommand with valid inputs.
     * @throws AdoException If task name is empty or date is empty/invalid.
     */
    public static DeadlineCommand validateDeadlineCommand(String commandDetails) throws AdoException {
        //checks if input has text after first word
        validateMultipleCommand(commandDetails);

        String[] deadlineSegments = commandDetails.split("/by", 2);
        boolean DateIsMissing = deadlineSegments.length < 2 && !deadlineSegments[0].contains("/by");
        boolean DescriptionIsMissing = deadlineSegments.length == 2 && deadlineSegments[0].trim().isEmpty();

        //Handles missing date in input
        if (DateIsMissing) {
            throw new AdoException(Constants.MISSING_DEADLINEDATE_MESSAGE);
        }
        //Handles empty description in input
        if (DescriptionIsMissing) {
            throw new AdoException(Constants.MISSING_DESCRIPTION_MESSAGE);
        }

        //Handles invalid date format in input
        String by = deadlineSegments[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
        LocalDate taskDeadlineDate;
        try {
            taskDeadlineDate = LocalDate.parse(by, formatter);

        } catch (Exception ex) {
            throw new AdoException(Constants.INVALID_DEADLINEDATE_MESSAGE);
        }

        String taskName = deadlineSegments[0].trim();
        return new DeadlineCommand(taskName, taskDeadlineDate);
    }

    /**
     * Checks if user input is valid for event commands.
     * @param commandDetails string of user's input after the first word.
     * @return EventCommand with valid inputs.
     * @throws AdoException If task name is empty or date/time is empty/invalid.
     */
    public static EventCommand validateEventCommand(String commandDetails) throws AdoException {
        //checks if input has text after first word
        validateMultipleCommand(commandDetails);

        String[] deadlineSegments = commandDetails.split("/at", 2);
        boolean DateIsMissing = deadlineSegments.length < 2 && !deadlineSegments[0].contains("/at");
        boolean DescriptionIsMissing = deadlineSegments.length == 2 && deadlineSegments[0].trim().isEmpty();

        //Handles missing date in input
        if (DateIsMissing) {
            throw new AdoException(Constants.MISSING_EVENTDATE_MESSAGE);
        }
        //Handles empty description in input
        if (DescriptionIsMissing) {
            throw new AdoException(Constants.MISSING_DESCRIPTION_MESSAGE);
        }

        //Handles invalid date format in input
        String at = deadlineSegments[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm", Locale.ENGLISH);
        LocalDateTime taskEventDate;
        try {
            taskEventDate = LocalDateTime.parse(at, formatter);
        } catch (Exception ex) {
            throw new AdoException(Constants.INVALID_EVENTDATE_MESSAGE);
        }

        String taskName = deadlineSegments[0].trim();
        return new EventCommand(taskName, taskEventDate);
    }

}
