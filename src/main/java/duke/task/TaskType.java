package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Enums for the possible tasks Duke can handle.
 */
public enum TaskType {
    /**
     * A to do task, which only has a description and no tied date/ time.
     */
    TODO {
        @Override
        public ToDo validateCommand(String cmd) throws DukeException {
            if (cmd.matches("(?i)^todo\\s.+")) {
                return new ToDo(cmd.substring(5));
            } else {
                throw new DukeException("hm... bobo's confused. Are you trying to create a todo?"
                        + "\nMake sure you follow the format: todo [description].\n"
                        + "The description of a todo cannot be empty!");
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ToDo parseSavedFormat(String savedFormat) throws DukeException {
            try {
                String[] savedData = TaskType.parseFormatArray(savedFormat);
                boolean status = savedData[0].equals("Y");
                String description = savedData[1];
                return new ToDo(description, status);
            } catch (ArrayIndexOutOfBoundsException e) {
                // task was saved in the wrong format for some reason
                throw new DukeException("Could not parse saved todo: " + savedFormat);
            }
        }
    },
    /**
     * An event task, which has a description and start/end datetime.
     */
    EVENT {
        @Override
        public Event validateCommand(String cmd) throws DukeException {
            // guard clause against wrongly formatted commands
            if (!cmd.matches("(?i)^event\\s.+\\s/(at)\\s.+\\s/(to)\\s.+")) {
                throw new DukeException("hm... bobo's confused. Are you trying to create an event?"
                        + "\nMake sure you follow the format: event [description] /at"
                        + " [event start datetime] /to [event end datetime]");
            }

            String[] sp = cmd.substring(6).split("\\s/((at)|(to))\\s", 3);
            String description = sp[0];
            String startDate = sp[1];
            String endDate = sp[2];
            LocalDateTime startDatetime = TaskType.dateTimeParser(startDate);
            LocalDateTime endDatetime = TaskType.dateTimeParser(endDate);

            // ensures that the start and end datetime is valid (start cannot be after end)
            if (startDatetime.isAfter(endDatetime)) {
                String errorMessage = String.format(END_BEFORE_START_ERROR_MESSAGE,
                        startDatetime, endDatetime);
                throw new DukeException(errorMessage);
            }

            return new Event(description, startDatetime, endDatetime);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Event parseSavedFormat(String savedFormat) throws DukeException {
            try {
                String[] savedData = TaskType.parseFormatArray(savedFormat);
                boolean status = savedData[0].equals("Y");
                String description = savedData[1];
                LocalDateTime startDatetime = LocalDateTime.parse(savedData[2]);
                LocalDateTime endDatetime = LocalDateTime.parse(savedData[3]);
                return new Event(description, startDatetime, endDatetime, status);
            } catch (ArrayIndexOutOfBoundsException e) {
                // task was saved in the wrong format for some reason
                throw new DukeException("Could not parse saved event: " + savedFormat);
            }
        }
    },
    /**
     * A deadline task, which has a description and needs to be done before a specific date/time.
     */
    DEADLINE {
        @Override
        public Deadline validateCommand(String cmd) throws DukeException {
            // guard clause against improperly formatted deadline commands
            if (!cmd.matches("(?i)^deadline\\s.+\\s/(by)\\s.+")) {
                throw new DukeException("hm... bobo's confused. Are you trying to create a deadline?"
                        + "\nMake sure you follow the format: deadline [description] /by [deadline]");
            }

            String[] sp = cmd.substring(9).split("/(by)\\s", 2);
            String description = sp[0];
            String dateTimeString = sp[1];
            LocalDateTime datetime = TaskType.dateTimeParser(dateTimeString);
            return new Deadline(description, datetime);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Deadline parseSavedFormat(String savedFormat) throws DukeException {
            try {
                String[] savedData = TaskType.parseFormatArray(savedFormat);
                boolean status = savedData[0].equals("Y");
                String description = savedData[1];
                LocalDateTime datetime = LocalDateTime.parse(savedData[2]);
                return new Deadline(description, datetime, status);
            } catch (ArrayIndexOutOfBoundsException e) {
                // task was saved in the wrong format for some reason
                throw new DukeException("Could not parse saved deadline: " + savedFormat);
            }
        }
    };

    private static final String END_BEFORE_START_ERROR_MESSAGE = "Start datetime %s cannot be after end datetime %s";
    private static final String UNKNOWN_DATE_FORMAT_ERROR = "Unknown date format %s!";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern(("dd/M/yyyy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d/MM/yyyy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d/M/yyyy")))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yy"))
            .appendOptional(DateTimeFormatter.ofPattern(("dd/M/yy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d/MM/yy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d/M/yy")))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern(("dd-M-yyyy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d-MM-yyyy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d-M-yyyy")))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yy"))
            .appendOptional(DateTimeFormatter.ofPattern(("dd-M-yy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d-MM-yy")))
            .appendOptional(DateTimeFormatter.ofPattern(("d-M-yy")))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yy hh:mm a"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yy hh:mm a"))
            .toFormatter();
    /**
     * Takes in a user's command and validates that it is a valid command (follows the
     * required format) based on the TaskType.
     *
     * @param cmd The user's command.
     * @return The task created by the command, provided the command is valid.
     * @throws DukeException Exception thrown when the provided command is invalid.
     */
    public abstract Task validateCommand(String cmd) throws DukeException;

    /**
     * Parses the saved format of a task into its corresponding task object.
     *
     * @param savedFormat The formatted task string from the saved file.
     * @return The corresponding task parsed from the saved data.
     * @throws DukeException If the string could not be parsed into a task.
     */
    public abstract Task parseSavedFormat(String savedFormat) throws DukeException;

    /**
     * Returns the TaskType represented by the specified character.
     *
     * @param data The character to be parsed into a TaskType.
     * @return The TaskType represented by the given character.
     * @throws DukeException If the given character is invalid.
     */
    public static TaskType readSavedTaskType(char data) throws DukeException {
        switch (data) {
        case 'T':
            return TaskType.TODO;
        case 'D':
            return TaskType.DEADLINE;
        case 'E':
            return TaskType.EVENT;
        default:
            throw new DukeException("Duke found an unknown data type: " + data);
        }
    }

    /**
     * Processes the saved data by extracting the deliminators and cleaning escaped characters into an array.
     *
     * @param savedFormat A line extracted from the file of saved tasks.
     * @return An array containing the processed data.
     */
    private static String[] parseFormatArray(String savedFormat) {
        String[] result = savedFormat.substring(4).split(" \\| ");
        int len = result.length;
        for (int i = 0; i < len; i++) {
            result[i] = result[i].replace("\\\\|", "|");
        }
        return result;
    }

    /**
     * Parses a given date or datetime string into a datetime object. If the string provided
     * is a date string, the time is set to the beginning of the day.
     *
     * @param dateTimeString The date or datetime string to be parsed.
     * @return A LocalDateTime object, where the time is set to the start of the day on
     *         default if the time is not specified.
     * @throws DukeException If the specified string could not be parsed into a datetime object.
     */
    private static LocalDateTime dateTimeParser(String dateTimeString) throws DukeException {
        LocalDateTime dateObject;
        try {
            dateObject = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            try {
                dateObject = LocalDate.parse(dateTimeString, DATE_TIME_FORMATTER).atStartOfDay();
            } catch (DateTimeParseException d) {
                throw new DukeException(String.format(UNKNOWN_DATE_FORMAT_ERROR, dateTimeString));
            }
        }
        return dateObject;
    }
}
