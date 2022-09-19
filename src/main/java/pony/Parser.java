package pony;

import pony.command.*;

/**
 * A parser class to parse command inputs.
 */
public class Parser {
    public Parser() {};

    /**
     * Parse the full command given by user.
     *
     * @param fullCommand Command given by user.
     * @return A command object depends on the user input.
     */
    public static Command parseCommand(String fullCommand) {
        String[] strArr = fullCommand.split(" ",2);
        String commandType = strArr[0];
        String commandDetails;

        if (strArr.length != 2) {
            commandDetails = null;
        } else {
            commandDetails = strArr[1];
        }

        switch (commandType) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(commandDetails);
            case "unmark":
                return new UnmarkCommand(commandDetails);
            case "delete":
                return new DeleteCommand(commandDetails);
            case "todo":
                return new AddToDoCommand(commandDetails);
            case "deadline":
                return new AddDeadlineCommand(commandDetails);
            case "event":
                return new AddEventCommand(commandDetails);
            case "find":
                return new FindCommand(commandDetails);
            default:
                return new InvalidCommand();
        }
    }

    /**
     * Parse a command expecting a task index input.
     *
     * @param commandDetails Command given by user.
     * @return Index of the task specified by the user command.
     * @throws PonyException User did not provide any details.
     * @throws NumberFormatException User did not provide an integer for task index.
     */
    public static int parseTaskIndex(String commandDetails) throws PonyException, NumberFormatException{
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            int taskIndex = Integer.parseInt(commandDetails);
            return taskIndex;
        }
    }

    /**
     * Parse a Todo command.
     *
     * @param commandDetails Command given by user.
     * @return Details of the task.
     * @throws PonyException User did not provide any details.
     */
    public static String parseTodoDetails(String commandDetails) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            return commandDetails;
        }
    }

    /**
     * Parse a Deadline command.
     *
     * @param commandDetails Command given by user.
     * @param format Format the command should follow.
     * @return Details of the task.
     * @throws PonyException User did not provide any details or not following the format.
     */
    public static String[] parseDeadlineDetails(String commandDetails, String format) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            String[] taskInfoArr = commandDetails.split(" /by ", 2);
            if (taskInfoArr.length != 2) {
                throw new PonyException(":( OOPS!!! Please provide the details in the following format: " + format);
            }
            return taskInfoArr;
        }
    }

    /**
     * Parse an Event command.
     *
     * @param commandDetails Command given by user.
     * @param format Format the command should follow.
     * @return Details of the task.
     * @throws PonyException User did not provide any detail or not following the format.
     */
    public static String[] parseEventDetails(String commandDetails, String format) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            String[] taskInfoArr = commandDetails.split(" /at ", 2);
            if (taskInfoArr.length != 2) {
                throw new PonyException(":( OOPS!!! Please provide the details in the following format: " + format);
            }
            return taskInfoArr;
        }
    }

    /**
     * Parse a Find command.
     *
     * @param commandDetails Command given by user.
     * @return Tasks found according to keyword.
     * @throws PonyException User did not provide any detail.
     */
    public static String parseFindDetails(String commandDetails) throws PonyException {
        if (commandDetails == null) {
            throw new PonyException(":( OOPS!!! Please provide the details!!");
        } else {
            return commandDetails;
        }
    }

}
