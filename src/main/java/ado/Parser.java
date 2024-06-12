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
            return getListCommand(commandDetails);
        case "bye":
            return getByeCommand(commandDetails);
        case "help":
            return getHelpCommand(commandDetails);
        case "todo":
            return validateTodoCommand(commandDetails);
        case "deadline":
            return validateDeadlineCommand(commandDetails);
        case "event":
            return validateEventCommand(fullCommand);
        case "mark":
            return getMarkCommand(fullCommand, inputSegments);
        case "unmark":
            return getUnmarkCommand(fullCommand, inputSegments);
        case "delete":
            return getDeleteCommand(fullCommand, inputSegments);
        case "find":
            return getFindCommand(fullCommand, inputSegments);
        default:
            throw new AdoException(commandFirstWord + Constants.INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Handles the creation of List Command object
     *
     * @param commandDetails the description for initialising a list command
     * @return the command of initialising a list command
     * @throws AdoException If command is invalid.
     */
    static ListCommand getListCommand(String commandDetails) throws AdoException {
        validateSingleCommand(commandDetails);
        return new ListCommand();
    }

    /**
     * Handles the creation of Bye Command object
     *
     * @param commandDetails the description for initialising a Bye command
     * @return the command of initialising a bye command
     * @throws AdoException If command is invalid.
     */
    static ByeCommand getByeCommand(String commandDetails) throws AdoException {
        validateSingleCommand(commandDetails);
        return new ByeCommand();
    }

    /**
     * Handles the creation of Help Command object
     *
     * @param commandDetails the description for initialising a Help command
     * @return the command of initialising a help command
     * @throws AdoException If command is invalid.
     */
    static HelpCommand getHelpCommand(String commandDetails) throws AdoException {
        validateSingleCommand(commandDetails);
        return new HelpCommand();
    }

    /**
     * Handles the creation of Mark Command object
     *
     * @param fullCommand the description for initialising a Mark command
     * @param inputSegments an array input split by spaces
     * @return the command of initialising a Mark command
     * @throws AdoException If command is invalid.
     */
    static MarkCommand getMarkCommand(String fullCommand, String[] inputSegments) throws AdoException {
        validateMultipleCommand(fullCommand);
        assert (inputSegments[1] != null) : "Missing index";
        return new MarkCommand(Integer.parseInt(inputSegments[1].trim()));
    }

    /**
     * Handles the creation of Unmark Command object
     *
     * @param fullCommand the description for initialising a Unmark command
     * @param inputSegments an array input split by spaces
     * @return the command of initialising a unmark command
     * @throws AdoException If command is invalid.
     */
    static UnmarkCommand getUnmarkCommand(String fullCommand, String[] inputSegments) throws AdoException {
        validateMultipleCommand(fullCommand);
        assert (inputSegments[1] != null) : "Missing index";
        return new UnmarkCommand(Integer.parseInt(inputSegments[1].trim()));
    }

    /**
     * Handles the creation of Delete Command object
     *
     * @param fullCommand the description for initialising a Delete command
     * @param inputSegments an array input split by spaces
     * @return the command of initialising a delete command
     * @throws AdoException If command is invalid.
     */
    static DeleteCommand getDeleteCommand(String fullCommand, String[] inputSegments) throws AdoException {
        validateMultipleCommand(fullCommand);
        assert (inputSegments[1] != null) : "Missing index";
        return new DeleteCommand(Integer.parseInt(inputSegments[1].trim()));
    }

    /**
     * Handles the creation of Find Command object
     *
     * @param fullCommand the description for initialising a Find command
     * @param inputSegments an array input split by spaces
     * @return the command of initialising a find command
     * @throws AdoException If command is invalid.
     */
    static FindCommand getFindCommand(String fullCommand, String[] inputSegments) throws AdoException {
        validateMultipleCommand(fullCommand);
        assert (inputSegments[1] != null) : "Missing find keyword";
        return new FindCommand(inputSegments[1].trim());
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
        boolean dateIsMissing = deadlineSegments.length < 2 && !deadlineSegments[0].contains("/by");
        boolean descriptionIsMissing = deadlineSegments.length == 2 && deadlineSegments[0].trim().isEmpty();

        //Handles missing date in input
        if (dateIsMissing) {
            throw new AdoException(Constants.MISSING_DEADLINEDATE_MESSAGE);
        }
        //Handles empty description in input
        if (descriptionIsMissing) {
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
        boolean dateIsMissing = deadlineSegments.length < 2 && !deadlineSegments[0].contains("/at");
        boolean descriptionIsMissing = deadlineSegments.length == 2 && deadlineSegments[0].trim().isEmpty();

        //Handles missing date in input
        if (dateIsMissing) {
            throw new AdoException(Constants.MISSING_EVENTDATE_MESSAGE);
        }
        //Handles empty description in input
        if (descriptionIsMissing) {
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
