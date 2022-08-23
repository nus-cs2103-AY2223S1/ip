package utility;
import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parses all lines to be stored or retrieved from file.
 */
public class StorageParser {
    public static String storableTaskDescription(String command, int n) {

    }

    /**
     * Converts line in disk file to corresponding Task.
     * @param line String stored in file.
     * @return Task object.
     */
    public static ArrayList<String> fileLineToTask(String line) throws DukeException {
        try {
            ArrayList a = new ArrayList();
            String taskType, description;
            int n = line.length();
            boolean marked;
            if (n <= 0) {
                return null;
            }
            taskType = line.substring(1, 2);
            marked = line.charAt(4) == ' ';
            switch (taskType) {
            case "T":
                description = line.substring(6);
                Task t = new Task(description);
                a.add(description);
                if (marked) {
                    a.add("done");
                }
                break;
            case "D":
                description = line.substring(6, line.indexOf("("));
                if (marked) {
                    a.add("done");
                }
                a.add(getDateFromStorage(line, " by: "));
                //fallthrough
            case "E":
                description = line.substring(6, line.indexOf("("));
                if (marked) {
                    a.add("done");
                }
                a.add(getDateFromStorage(line, " by: "));
            }
            return a;
        } catch (Exception e) {
            throw new DukeException("file corrupted1");
        }
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
