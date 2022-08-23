package utility;
import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses all lines to be stored or retrieved from file.
 */
public class StorageParser {
    public static String storableTaskDescription(Task t) {
        return t.toString() + "\n";
    }
    public static String storableEventDescription(Event e) {
        return e.toString() + "\n";
    }
    public static String storableDeadlineDescription(Deadline d) {
        return d.toString() + "\n";
    }
    /**
     * Converts line in disk file to corresponding Task.
     * @param line String stored in file.
     * @return Task object.
     */
    public static Task fileLineToTask(String line) throws DukeException {
        try {
            String taskType, description;
            boolean marked;
            taskType = line.substring(1, 2);
            marked = line.charAt(4) == ' ';
            switch (taskType) {
            case "T":
                description = line.substring(6);
                Task t = new Task(description);
                if (marked) {
                    t.markAsDone();
                }
                return t;
            case "D":
                description = line.substring(6, line.indexOf("("));
                Deadline d = new Deadline(description, getDateFromStorage(line, "by: "));
                if (marked) {
                    d.markAsDone();
                }
                return d;
            case "E":
                description = line.substring(6, line.indexOf("("));
                Event e = new Event(description, getDateFromStorage(line, "at: "));
                if (marked) {
                    e.markAsDone();
                }
                return e;
            }
        } catch (Exception e) {
            throw new DukeException("file corrupted");
        }
        throw new DukeException("file corrupted");
    }


    /**
     * Return LocalDate object stored from the format its stored
     * in file.
     * @param line line retrieved from file.
     * @param compareString command to watch for in line.
     * @return LocalDate object
     * @throws DukeException exception.
     * @throws DateTimeParseException when date stored was invalid.
     */
    public static LocalDate getDateFromStorage(String line, String compareString) throws DukeException, DateTimeParseException {
        try {
            String date;
            int NUM_CHARACTERS_TO_CHECK = compareString.length();
            int DATE_LENGTH = 10;
            int startOfDateIndex = line.indexOf(compareString) + NUM_CHARACTERS_TO_CHECK;
            date = line.substring(startOfDateIndex, startOfDateIndex + DATE_LENGTH).trim();
            return LocalDate.parse(date);
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Date in storage is invalid");
        }
    }
}
