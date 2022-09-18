package candice;

import candice.command.Command;
import candice.command.CommandType;
import candice.exception.EmptyCommandDescriptionException;
import candice.exception.EmptyTimingException;
import candice.exception.InvalidDateException;
import candice.exception.InvalidFormattingException;
import candice.exception.InvalidTimeException;
import candice.exception.UnknownCommandException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Parses inputs and returns Action objects that can be resolved or throws Exceptions if the input cannot be parsed.
 */
public class Parser {
    /**
     * Parses the name of the task before using the method parseDateAndTime as a helper to return a TimedTaskCommand.
     * This method also checks for any formatting errors, specifically if /by was used for Deadlines and /at was used
     * for Events.
     *
     * @param taskType The CommandType of the command given, Deadline or Event.
     * @param taskDescription The description of the task (name of the task, the timed task date and timed task timing).
     * @return An instance of a TimedTaskCommand encapsulating the command given.
     * @throws EmptyTimingException If there was no date and time given for a deadline or event task.
     * @throws InvalidDateException If there was a date inputted that does not follow the format or does not exist.
     * @throws InvalidFormattingException If there was a command inputted that did not follow the format.
     * @throws InvalidTimeException If there was a time inputted that was not a 4 digit 24-hour time.
     */
    private static Command.TimedTaskCommand parseTimedTaskCommand(CommandType taskType, String taskDescription)
            throws EmptyTimingException, InvalidDateException, InvalidFormattingException, InvalidTimeException {
        assert taskType == CommandType.DEADLINE || taskType == CommandType.EVENT;

        String[] splitTaskAndTimestamp = taskDescription.split(" /", 2);

        if (splitTaskAndTimestamp.length == 2) {
            String taskName = splitTaskAndTimestamp[0];
            String timestamp = splitTaskAndTimestamp[1];

            String[] splitTimestamp = timestamp.split(" ", 2);

            if ((taskType == CommandType.DEADLINE && splitTimestamp[0].equals("by"))
                    || (taskType == CommandType.EVENT && splitTimestamp[0].equals("at"))) {
                if (splitTimestamp.length == 2) {
                    return parseDateAndTime(taskType, taskName, splitTimestamp[1]);
                } else {
                    throw new EmptyTimingException(taskType);
                }
            } else {
                throw new InvalidFormattingException(taskType);
            }
        } else {
            throw new EmptyTimingException(taskType);
        }
    }

    /**
     * Parses the date of the command inputted and immediately returns the TimedTaskCommand if no time was given.
     * If a time was given, the method parseDeadlineTime is used for Deadline commands and the method parseEventTime is
     * used for Event commands.
     *
     * @param taskType The CommandType of the command given, Deadline or Event.
     * @param taskName The name of the task in the command given.
     * @param taskDateAndTime The date and time of the command given.
     * @return An instance of a TimedTaskCommand encapsulating the command given.
     * @throws InvalidDateException If there was a date inputted that does not follow the format or does not exist.
     * @throws InvalidFormattingException If there was a command inputted that did not follow the format.
     * @throws InvalidTimeException If there was a time inputted that was not a 4 digit 24-hour time.
     */
    private static Command.TimedTaskCommand parseDateAndTime(CommandType taskType, String taskName,
            String taskDateAndTime) throws InvalidDateException, InvalidFormattingException, InvalidTimeException {
        String[] splitDateAndTime = taskDateAndTime.split(" ");

        if (splitDateAndTime.length > 2) {
            throw new InvalidFormattingException(taskType);
        }

        LocalDate taskDate;

        String taskDateAsString = splitDateAndTime[0];

        String[] splitDayMonthYear = taskDateAsString.split("/");
        if (splitDayMonthYear.length == 3) {
            StringBuilder day = new StringBuilder(splitDayMonthYear[0]);
            StringBuilder month = new StringBuilder(splitDayMonthYear[1]);
            StringBuilder year = new StringBuilder(splitDayMonthYear[2]);

            int dayLength = day.length();
            int monthLength = month.length();
            int yearLength = year.length();

            // Filling the days, months and years to match the format required for LocalDate
            for (int i = 0; i < (2 - dayLength); i++) {
                day.insert(0, "0");
            }

            for (int i = 0; i < (2 - monthLength); i++) {
                month.insert(0, "0");
            }

            for (int i = 0; i < (4 - yearLength); i++) {
                year.insert(0, "0");
            }

            String localDateFormat = year + "-" + month + "-" + day;

            try {
                taskDate = LocalDate.parse(localDateFormat);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } else {
            throw new InvalidDateException();
        }

        if (splitDateAndTime.length == 2) { // Time was provided
            if (taskType == CommandType.DEADLINE) {
                return parseDeadlineTime(taskName, taskDate, splitDateAndTime[1]);
            } else {
                assert taskType == CommandType.EVENT;
                return parseEventTime(taskName, taskDate, splitDateAndTime[1]);
            }
        } else if (taskType == CommandType.DEADLINE) { // No time was provided
            return new Command.DeadlineCommand(taskName, taskDate, null);
        } else { // No time was provided and taskType is event
            assert taskType == CommandType.EVENT;
            return new Command.EventCommand(taskName, taskDate, null, null);
        }
    }

    /**
     * Parses the deadline time of the command inputted to return the DeadlineCommand object.
     *
     * @param taskName The name of the task in the command given.
     * @param deadlineDate The deadline date of the command given.
     * @param deadlineTimeAsString The deadline time of the command given.
     * @return An instance of a DeadlineCommand encapsulating the command given.
     * @throws InvalidTimeException If there was a time inputted that was not a 4 digit 24-hour time.
     */
    private static Command.DeadlineCommand parseDeadlineTime(String taskName, LocalDate deadlineDate,
            String deadlineTimeAsString) throws InvalidTimeException {
        if (deadlineTimeAsString.length() == 4) {
            String hour = deadlineTimeAsString.substring(0, 2);
            String minute = deadlineTimeAsString.substring(2);
            String localTimeFormat = hour + ":" + minute;
            LocalTime deadlineTime;

            try {
                deadlineTime = LocalTime.parse(localTimeFormat);
            } catch (DateTimeParseException e) {
                throw new InvalidTimeException();
            }

            return new Command.DeadlineCommand(taskName, deadlineDate, deadlineTime);
        } else {
            throw new InvalidTimeException();
        }
    }

    /**
     * Parses the event start and end time of the command inputted to return the EventCommand object.
     *
     * @param taskName The name of the task in the command given.
     * @param eventDate The event date of the command given.
     * @param eventTimeAsString The event time of the command given.
     * @return An instance of a EventCommand encapsulating the command given.
     * @throws InvalidTimeException If there was a time inputted that was not a 4 digit 24-hour time.
     */
    private static Command.EventCommand parseEventTime(String taskName, LocalDate eventDate, String eventTimeAsString)
            throws InvalidTimeException {
        if (eventTimeAsString.length() == 9) {
            String[] splitStartEndTime = eventTimeAsString.split("-");
            if (splitStartEndTime.length == 2) {
                LocalTime eventStartTime;
                LocalTime eventEndTime;
                // Changing to LocalTime format
                for (int i = 0; i < 2; i++) {
                    String eventTime = splitStartEndTime[i];
                    String hour = eventTime.substring(0, 2);
                    String minute = eventTime.substring(2);
                    String localTimeFormat = hour + ":" + minute;
                    splitStartEndTime[i] = localTimeFormat;
                }

                try {
                    eventStartTime = LocalTime.parse(splitStartEndTime[0]);
                    eventEndTime = LocalTime.parse(splitStartEndTime[1]);
                } catch (DateTimeParseException e) {
                    throw new InvalidTimeException();
                }

                return new Command.EventCommand(taskName, eventDate, eventStartTime, eventEndTime);
            } else {
                throw new InvalidTimeException();
            }
        } else {
            throw new InvalidTimeException();
        }
    }

    /**
     * Parses the command given to return a Command object encapsulating the command just given.
     * The Command object can then be resolved to execute the programme.
     *
     * @param command The command given.
     * @return The corresponding Command object that encapsulates the command given.
     * @throws EmptyCommandDescriptionException If there was a command with no task name given.
     * @throws EmptyTimingException If there was no date and time given for a deadline or event task.
     * @throws InvalidDateException If there was a date inputted that does not follow the format or does not exist.
     * @throws InvalidFormattingException If there was a command inputted that did not follow the format.
     * @throws InvalidTimeException If there was a time inputted that was not a 4 digit 24-hour time.
     * @throws UnknownCommandException If a command type was unrecognised.
     */
    public static Command parse(String command) throws EmptyCommandDescriptionException, EmptyTimingException,
            InvalidDateException, InvalidFormattingException, InvalidTimeException, UnknownCommandException {
        String[] commandSplit = command.split(" ", 2);

        String commandTypeAsString = commandSplit[0];
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandTypeAsString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        
        switch (commandType) {
        case BYE:
        case LIST:
            if (commandSplit.length == 2) { // Should only have "bye" or "list"
                throw new UnknownCommandException();
            } else {
                return new Command.SingleWordCommand(commandType);
            }
        case TODO:
        case FIND:
            if (commandSplit.length == 2) {
                return new Command.NonTimedCommand(commandType, commandSplit[1]);
            } else {
                throw new EmptyCommandDescriptionException(commandType);
            }
        case MARK:
        case UNMARK:
        case DELETE:
            if (commandSplit.length == 2) {
                try {
                    int taskNumber = Integer.parseInt(commandSplit[1]);
                    return new Command.NumberedCommand(commandType, taskNumber);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Write properly leh. Your number format is wrong.");
                }
            } else {
                throw new EmptyCommandDescriptionException(commandType);
            }
        case DEADLINE:
        case EVENT:
            if (commandSplit.length == 2) {
                return parseTimedTaskCommand(commandType, commandSplit[1]);
            } else {
                throw new EmptyCommandDescriptionException(commandType);
            }
        default:
            throw new UnknownCommandException();
        }
    }
}
