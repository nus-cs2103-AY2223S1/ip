package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Contains methods to parse various forms of user input to the chatbot.
 * 
 * @author Siau Wee
 */
public class Parser {
    
    /**
     * Parses a date and time string from user input to follow a predetermined format.
     * 
     * @param dateTime The user-entered date and time string.
     * @return The parsed date and time, wrapped in a LocalDateTime object 
     * @throws IllegalDateTimeException If date and time string does not
     *                                  follow the defined format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws IllegalDateTimeException {
        // abide by format dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)
        assert dateTime != null;
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
     * Parses a date and time string from the save file to follow a predetermined format.
     *
     * @param dateTime The user-entered date and time string.
     * @return The parsed date and time, wrapped in a LocalDateTime object
     * @throws IllegalDateTimeException If date and time string does not
     *                                  follow the defined format.
     */
    public static LocalDateTime parseSavedDateTime(String dateTime) throws IllegalDateTimeException {
        String[] date = dateTime.split(" ")[0].split("/");
        String[] time = dateTime.split(" ")[1].split(":");
        try {
            LocalDateTime localDateTime = LocalDateTime.of(
                    Integer.parseInt(date[2]),
                    Integer.parseInt(date[1]),
                    Integer.parseInt(date[0]),
                    Integer.parseInt(time[0]),
                    Integer.parseInt(time[1])
            );
            return localDateTime;
        } catch (NumberFormatException e) {
            throw new IllegalDateTimeException(dateTime);
        } catch (DateTimeException e) {
            throw new IllegalDateTimeException(dateTime);
        }
    }

    /**
     * Parse and create a task based on the string loaded from the save file
     * @param lineFromFile The string representing the line loaded from save file
     * @return The created task
     * @throws IOException If errors occur when reading input from file.
     */
    public static Task parseLoadedTask(String lineFromFile) throws IOException {
        final String PREFIX_TODO = "[T]";
        final String PREFIX_DEADLINE = "[D]";
        final String PREFIX_EVENT = "[E]";
        String[] lineDetails = lineFromFile.split("] ", 2);
        String linePrefix = lineDetails[0].substring(0, 3);
        System.out.println(linePrefix);
        boolean lineMarked = lineDetails[0].charAt(4) == 'X';
        try {
            if (linePrefix.equals(PREFIX_TODO)) {
                return new Todo(lineDetails[1], lineMarked);
            } else if (linePrefix.equals(PREFIX_DEADLINE)) {
                String[] deadlineDetails = lineDetails[1].split(" ", 3);
                return new Deadline(
                        deadlineDetails[0],
                        Parser.parseSavedDateTime(deadlineDetails[2]),
                        lineMarked
                );
            } else if (linePrefix.equals(PREFIX_EVENT)) {
                String[] eventDetails = lineDetails[1].split(" ", 3);
                return new Event(
                        eventDetails[0],
                        Parser.parseSavedDateTime(eventDetails[2]),
                        lineMarked
                );
            } else {
                throw new IOException("Unknown task type");
            }
        } catch (IllegalDateTimeException e) {
            System.out.println(e.getMessage());
            return null;
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
        case "sortdate":
            return new Command(Keyword.SORT_DATE, commandArgs);
        case "sortname":
            return new Command(Keyword.SORT_NAME, commandArgs);
        default:
            throw new CommandNotFoundException(commandArgs[0]);
        }
    }
}
