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
    /**
     * Converts line in disk file to corresponding Task.
     * @param line String stored in file.
     * @return Task object.
     */
    public static ArrayList<String> fileLineToTask(String line) throws DukeException {
       ArrayList<String> a = new ArrayList<>();
       String type = line.substring(1,2);
       a.add(type);
       String isDone = line.substring(4,5).equals(" ")? "not done": "done";
       a.add(isDone);
       String description;
       if (type.equals("T")) {
           description = line.substring(7);
           a.add(description);
       } else if (type.equals("D")) {
           description = line.substring(7, line.indexOf("(") + 1);
           a.add(description);
           int startDate = line.indexOf(": ") + 2;
           String date = line.substring(startDate, startDate + 10 );
           a.add(date);
       } else if (type.equals("E")) {
           description = line.substring(7, line.indexOf(" ("));
           a.add(description);
           int startDate = line.indexOf(": ") + 2;
           String date = line.substring(startDate, startDate + 10 );
           a.add(date);
       }
       return a;
    }

}
