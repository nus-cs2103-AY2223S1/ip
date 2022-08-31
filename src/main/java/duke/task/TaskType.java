package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                throw new DukeException("Hm... Duke's confused. Are you trying to create a todo?"
                        + "\nMake sure you follow the format: todo [description].\n"
                        + "The description of a todo cannot be empty!");
            }
        }

        @Override
        public ToDo parseSavedFormat(String savedFormat) throws DukeException {
            try {
                String[] savedData = TaskType.parseFormatArray(savedFormat);
                ;
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
            if (cmd.matches("(?i)^event\\s.+\\s\\/(at)\\s.+\\s\\/(to)\\s.+")) {
                String[] sp = cmd.substring(6).split("\\s\\/(((at))|((to)))\\s", 3);
                LocalDateTime startDatetime;
                LocalDateTime endDatetime;
                try {
                    startDatetime = LocalDate.parse(sp[1], DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
                } catch (DateTimeParseException e) {
                    try {
                        startDatetime = LocalDateTime.parse(sp[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    } catch (DateTimeParseException err) {
                        throw new DukeException(String.format("Unknown date format %s!", sp[1]));
                    }
                }
                try {
                    endDatetime = LocalDate.parse(sp[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
                } catch (DateTimeParseException e) {
                    try {
                        endDatetime = LocalDateTime.parse(sp[2], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    } catch (DateTimeParseException err) {
                        throw new DukeException(String.format("Unknown date format %s!", sp[2]));
                    }
                }
                return new Event(sp[0], startDatetime, endDatetime);

            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create an event?"
                        + "\nMake sure you follow the format: event [description] /at [event start datetime DD/MM/YYYY"
                        + " (optional: HH:MM)] /to [event end datetime DD/MM/YYYY (optional: HH:MM)]");
            }
        }

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
            if (cmd.matches("(?i)^deadline\\s.+\\s\\/(by)\\s.+")) {
                String[] sp = cmd.substring(9).split("\\/(by)\\s", 2);
                LocalDateTime datetime;
                try {
                    datetime = LocalDate.parse(sp[1], DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
                } catch (DateTimeParseException e) {
                    try {
                        datetime = LocalDateTime.parse(sp[1], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    } catch (DateTimeParseException err) {
                        throw new DukeException("Unknown date format!");
                    }
                }
                return new Deadline(sp[0], datetime);
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create a deadline?"
                        + "\nMake sure you follow the format: deadline [description] /by [deadline]");
            }
        }

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

    /**
     * Takes in a user's command and validates that it is a valid command (follows the
     * required format) based on the TaskType.
     *
     * @param cmd The user's command
     * @return The task created by the command, provided the command is valid
     * @throws DukeException Exception thrown when the provided command is invalid.
     */
    public abstract Task validateCommand(String cmd) throws DukeException;

    public abstract Task parseSavedFormat(String savedFormat) throws DukeException;

    /**
     * Returns the TaskType represented by the specified character.
     *
     * @param data The character to be parsed into a TaskType
     * @return The TaskType represented by the given character
     * @throws DukeException If the given character is invalid
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
     * @param savedFormat A line extracted from the file of saved tasks
     * @return An array containing the processed data
     */
    private static String[] parseFormatArray(String savedFormat) {
        String[] result = savedFormat.substring(4).split("\\ \\|\\ ");
        int len = result.length;
        for (int i = 0; i < len; i++) {
            result[i] = result[i].replace("\\\\|", "|");
        }
        return result;
    }
}
