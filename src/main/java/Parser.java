import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Parses inputs and returns Action objects that can be resolved or throws Exceptions if the input cannot be parsed.
 */
public class Parser {

    private static Command.TimedTaskCommand parseTimedTaskCommand(CommandType taskType, String taskDescription)
            throws EmptyTimingException, InvalidDateException, InvalidFormattingException, InvalidTimeException {
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

    private static Command.TimedTaskCommand parseDateAndTime(CommandType taskType, String taskName, String taskDateAndTime)
            throws InvalidDateException, InvalidFormattingException, InvalidTimeException {
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
                return parseEventTime(taskName, taskDate, splitDateAndTime[1]);
            }
        } else if (taskType == CommandType.DEADLINE) { // No time was provided
            return new Command.DeadlineCommand(taskName, taskDate, null);
        } else { // No time was provided and taskType is event
            return new Command.EventCommand(taskName, taskDate, null, null);
        }
    }

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
     * @throws EmptyTaskNameException If there was a command with no task name given.
     * @throws EmptyTimingException If there was no date and time given for a deadline or event task.
     * @throws InvalidDateException If there was a date inputted that does not follow the format or does not exist.
     * @throws InvalidFormattingException If there was a command inputted that did not follow the format.
     * @throws InvalidTimeException If there was a time inputted that was not a 4 digit 24-hour time.
     * @throws UnknownCommandException If a command type was unrecognised.
     */
    public static Command parse(String command) throws EmptyTaskNameException, EmptyTimingException,
            InvalidDateException, InvalidFormattingException, InvalidTimeException, UnknownCommandException {
        String[] commandSplit = command.split(" ", 2);

        String commandType = commandSplit[0];

        switch (commandType) {
        case "bye":
        case "list":
            if (commandSplit.length == 2) { // Should only have "bye" or "list"
                throw new UnknownCommandException();
            } else {
                return new Command.SingleWordCommand(CommandType.valueOf(commandType.toUpperCase()));
            }
        case "todo":
            if (commandSplit.length == 2) {
                return new Command.NonTimedTaskCommand(commandSplit[1]);
            } else {
                throw new EmptyTaskNameException(CommandType.TODO);
            }
        case "mark":
        case "unmark":
        case "delete":
            if (commandSplit.length == 2) {
                try {
                    int taskNumber = Integer.parseInt(commandSplit[1]);
                    return new Command.NumberedCommand(CommandType.valueOf(commandType.toUpperCase()), taskNumber);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Write properly leh. Your number format is wrong.");
                }
            } else {
                throw new EmptyTaskNameException(CommandType.valueOf(commandType.toUpperCase()));
            }
        case "deadline":
        case "event":
            if (commandSplit.length == 2) {
                return parseTimedTaskCommand(CommandType.valueOf(commandType.toUpperCase()), commandSplit[1]);
            } else {
                if (commandType.equals("deadline")) {
                    throw new EmptyTaskNameException(CommandType.DEADLINE);
                } else {
                    throw new EmptyTaskNameException(CommandType.EVENT);
                }
            }
        default:
            throw new UnknownCommandException();
        }
    }
}
