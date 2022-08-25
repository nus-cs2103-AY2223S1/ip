package utility;

import java.time.LocalDate;

import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;

/**
 * Parses all lines to be stored or retrieved from file.
 */
public class StorageParser {
    /**
     * Converts line in disk file to corresponding Task.
     * @param line String stored in file.
     * @return Task object.
     */
    public static Task fileLineToTask(String line) throws DukeException {
        String type = line.substring(1, 2);
        String doneString = line.substring(4, 5).equals(" ") ? "not done" : "done";
        boolean isDone = doneString.equals("done");
        String description;
        Task t;
        Deadline d;
        Event e;
        if (type.equals("T")) {
            description = line.substring(7);
            t = new Task(description);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else if (type.equals("D")) {
            description = line.substring(7, line.indexOf(" ("));
            int startDate = line.indexOf(": ") + 2;
            String date = line.substring(startDate, startDate + 10);
            d = new Deadline(description, LocalDate.parse(date));
            if (isDone) {
                d.markAsDone();
            }
            return d;
        } else if (type.equals("E")) {
            description = line.substring(7, line.indexOf(" ("));
            int startDate = line.indexOf(": ") + 2;
            String date = line.substring(startDate, startDate + 10);
            e = new Event(description, LocalDate.parse(date));
            if (isDone) {
                e.markAsDone();
            }
            return e;
        }
        return null;
    }


}
