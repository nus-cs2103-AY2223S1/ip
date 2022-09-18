package kkbot.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kkbot.commands.ByeCommand;
import kkbot.commands.Command;
import kkbot.commands.DeadlineCommand;
import kkbot.commands.DeleteCommand;
import kkbot.commands.EventCommand;
import kkbot.commands.ListCommand;
import kkbot.commands.MarkCommand;
import kkbot.commands.ToDoCommand;
import kkbot.commands.UnmarkCommand;
import kkbot.exceptions.KKBotException;
import kkbot.parser.exceptions.InvalidArgumentException;
import kkbot.parser.exceptions.InvalidCommandException;
import kkbot.parser.exceptions.InvalidDateException;

/**
 * Parser class to process user input for kkbot.kkbot
 *
 * @author AkkFiros
 */

public class Parser {
    private static final String INVALID_INPUT = "Invalid user input, please try again.";
    private enum MissingDetails {
        TASK_NUMBER, DESCRIPTION, DESCRIPTION_AND_DATE, DATE, KEYWORD
    }
    private static final String DATE_FORMAT = "yyyy-mm-dd";

    /**
     * Parse method to determine the command to pass to kkbot.kkbot.
     * @param input the user input
     * @return the command to pass to kkbot.kkbot
     * @throws KKBotException when the user input is erroneous
     */
    public static Command initialParse(String input) throws KKBotException {
        String[] splitInput = input.trim().split(" ", 2);
        String inputCommand = splitInput[0];

        switch (inputCommand) {
            case ByeCommand.KEYWORD:
                return new ByeCommand();
            case ListCommand.KEYWORD:
                return new ListCommand();
            case MarkCommand.KEYWORD:
            case UnmarkCommand.KEYWORD:
            case DeleteCommand.KEYWORD:
                return parseForIndex(splitInput, inputCommand);
            case ToDoCommand.KEYWORD:
                return parseForToDo(splitInput);
            case DeadlineCommand.KEYWORD:
            case EventCommand.KEYWORD:
                return parseForDate(splitInput, inputCommand);
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Method to parse for a user-specified index number.
     * @param splitInput array of input components after initial parse
     * @param inputCommand the command input by user
     * @return the command after retrieving the user input task index number
     * @throws InvalidCommandException when user input a wrong command
     * @throws InvalidArgumentException when user input is invalid
     */
    private static Command parseForIndex(String[] splitInput, String inputCommand)
            throws InvalidCommandException, InvalidArgumentException {
        checkInputLength(splitInput, MissingDetails.TASK_NUMBER);
        assert splitInput.length == 2 : INVALID_INPUT;
        int index = getTaskNumber(splitInput[1]);
        switch (inputCommand) {
            case "mark":
                return new MarkCommand(index);
            case"unmark":
                return new UnmarkCommand(index);
            case "delete":
                return new DeleteCommand(index);
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Method to parse for a user-input description for a todo task
     * @param splitInput array of input components after initial parse
     * @return the command to create a ToDo task
     * @throws InvalidArgumentException when user input is invalid
     */
    private static Command parseForToDo(String[] splitInput)
            throws InvalidArgumentException {
        checkInputLength(splitInput, MissingDetails.DESCRIPTION);
        assert splitInput.length == 2 : INVALID_INPUT;
        String description = splitInput[1].trim();
        return new ToDoCommand(description);
    }

    /**
     * Method to parse user-input for tasks that involve a date
     * @param splitInput array of input components after initial parse
     * @param type Either a deadline or event type task
     * @return the command to create a Deadline or Event task
     * @throws InvalidArgumentException when user input is invalid
     * @throws InvalidDateException when user input date is invalid
     */
    private static Command parseForDate(String[] splitInput, String type)
            throws InvalidArgumentException, InvalidDateException {
        checkInputLength(splitInput, MissingDetails.DESCRIPTION_AND_DATE);
        assert splitInput.length == 2 : INVALID_INPUT;
        String description = splitInput[1];
        boolean isDeadline = type.equals("deadline");
        String[] splitDescription;
        if (isDeadline) {
            splitDescription = description.split(DeadlineCommand.DATE_INPUT, 2);
        } else {
            splitDescription = description.split(EventCommand.DATE_INPUT, 2);
        }
        checkInputLength(splitDescription, MissingDetails.DESCRIPTION_AND_DATE);
        assert splitDescription.length == 2 : INVALID_INPUT;
        String date = splitDescription[1].trim();
        checkDateFormat(date);
        String taskDescription = splitDescription[0].trim();
        if (isDeadline) {
            return new DeadlineCommand(taskDescription, date);
        } else {
            return new EventCommand(taskDescription, date);
        }
    }

    /**
     * Method to check the length of a user input
     * @param splitInput array of input components after initial parse
     * @param details Enum of possible missing details in user input
     * @throws InvalidArgumentException when user input is invalid
     */
    private static void checkInputLength(String[] splitInput, MissingDetails details)
            throws InvalidArgumentException {
        if (splitInput.length < 2) {
            switch (details) {
                case TASK_NUMBER:
                    throw new InvalidArgumentException("You missed out a task number, please input one!");
                case DESCRIPTION:
                    throw new InvalidArgumentException("You missed out a task description, please input one!");
                case DESCRIPTION_AND_DATE:
                    throw new InvalidArgumentException("You missed out a task description and date, please input one!");
                case DATE:
                    throw new InvalidArgumentException("You missed out a date, please input one!");
                case KEYWORD:
                    throw new InvalidArgumentException("You missed out a command, please input one!");
            }
        }
    }

    /**
     * Method to retrieve task number from user input by parsing
     * @param taskNumber the user input string to parse
     * @return the task number retrieved from user input
     * @throws InvalidArgumentException when user input is invalid
     */
    private static int getTaskNumber(String taskNumber)
            throws InvalidArgumentException {
        try {
            return Integer.parseInt(taskNumber.trim());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Woops! The task number isn't valid! Please correct it!");
        }
    }

    /**
     * Method to check if user-input date follows the proper format
     * @param date the user-input date
     * @throws InvalidDateException when user-input date is erroneous
     */
    public static void checkDateFormat(String date) throws InvalidDateException {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(DATE_FORMAT);
        }
    }

    /**
     * Method to parse user inputs to retrieve the date
     * @param date the user input to parse
     * @return returns the date in a desired format
     */
    public static String parseDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
