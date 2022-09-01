package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Contains methods to parse various forms of user input to the chatbot.
 * 
 * @author Siau Wee
 */
public class Parser {
    
    /**
     * Parses a date and time string to follow a predetermined format.
     * 
     * @param dateTime The user-entered date and time string.
     * @return The parsed date and time, wrapped in a LocalDateTime object 
     * @throws IllegalDateTimeException If date and time string does not
     *                                  follow the defined format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws IllegalDateTimeException {
        // abide by format dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)
        String[] dateTimeData = dateTime.split("-");
        if (dateTimeData.length != 5) {
            throw new IllegalDateTimeException(dateTime);
        } else {
            try {
                int[] dateTimeDataInt = new int[5];
                for (int i = 0; i < dateTimeData.length; ++i) {
                    dateTimeDataInt[i] = Integer.parseInt(dateTimeData[i]);
                }
                LocalDateTime localDateTime = LocalDateTime.of(
                        dateTimeDataInt[2], dateTimeDataInt[1],
                        dateTimeDataInt[0], dateTimeDataInt[3],
                        dateTimeDataInt[4]);
                return localDateTime;
            } catch (NumberFormatException e) {
                throw new IllegalDateTimeException(dateTime);
            } catch (DateTimeException e) {
                throw new IllegalDateTimeException(dateTime);
            }
        }
    }

    /**
     * Parses a user's command input.
     * 
     * @param commandArgs The user-entered command, split by keyword and arguments
     * @return The parsed command wrapped in a Command object
     * @throws DukeException If the user-entered command does not abide by format
     */
    public static Command parseCommand(String[] commandArgs)
            throws DukeException {
        String keyword = commandArgs[0];
        switch (keyword) {
        case "bye":
            return new Command(Keyword.BYE, commandArgs);
        case "list":
            return new Command(Keyword.LIST, commandArgs);
        case "mark":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            return new Command(Keyword.MARK, commandArgs);
        case "unmark":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            return new Command(Keyword.UNMARK, commandArgs);
        case "delete":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            return new Command(Keyword.DELETE, commandArgs);
        case "todo":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            return new Command(Keyword.TODO, commandArgs);
        case "deadline":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            String[] deadlineDetails = commandArgs[1].split(" /by ");
            if (deadlineDetails.length == 1) {
                throw new IllegalCommandException(commandArgs[0]);
            }
            return new Command(Keyword.DEADLINE, deadlineDetails);
        case "event":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            String[] eventDetails = commandArgs[1].split(" /at ");
            if (eventDetails.length <= 1) {
                throw new IllegalCommandException(commandArgs[0]);
            }
            return new Command(Keyword.EVENT, eventDetails);
        case "find":
            if (commandArgs.length == 1) {
                throw new EmptyCommandException(commandArgs[0]);
            }
            return new Command(Keyword.FIND, commandArgs);
        default:
            throw new CommandNotFoundException(commandArgs[0]);
        }
    }
}
