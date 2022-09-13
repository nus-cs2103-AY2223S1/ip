package utility;

import java.time.LocalDate;

import duke.Duke;
import exceptions.DukeException;
import task.Deadline;
import task.Event;
import task.Task;

/**
 * Parses all lines to be stored or retrieved from file.
 */
public class StorageParser {
    private static final int DESCRIPTION = 0;
    private static final int DATE = 1;
    private static final int START_OF_DESCRIPTION_IN_TASK = 7;
    private static final int MARKED_STATUS = 4;
    private static final String END_OF_DESCRIPTION_STRING = " (";
    private static final String START_OF_DATE_STRING = ": ";
    private static final int START_OF_DATE_STRING_OFFSET = 2;
    private static final int DATE_LENGTH = 10;
    private static final char UNMARKED_SYMBOL = ' ';
    private static final int INDEX_OF_TYPE_CHAR = 1;

    /**
     * Converts line in disk file to corresponding Task.
     *
     * @param line String stored in file.
     * @return Task object.
     */
    public static Task fileLineToTask(String line) throws DukeException {
        char type = typeOfRecord(line);
        boolean isMarked = isTaskMarked(line);
        String[] dateAndDescription;
        Task t = null;
        switch(type) {
        case 'T':
            String taskDescription = getTaskDescription(line);
            t = createTask(taskDescription, isMarked);
            break;
        case 'D':
            dateAndDescription = getDateAndDescription(line);
            t = createDeadline(dateAndDescription[DESCRIPTION],
                    dateAndDescription[DATE], isMarked);
            break;
        case 'E':
            dateAndDescription = getDateAndDescription(line);
            t = createEvent(dateAndDescription[DESCRIPTION],
                    dateAndDescription[DATE], isMarked);
            break;
        default:
            // do nothing
        }
        return t;
    }

    private static boolean isTaskMarked(String task) {
        return task.charAt(MARKED_STATUS) != UNMARKED_SYMBOL;
    }

    private static char typeOfRecord(String record) {
        return record.charAt(INDEX_OF_TYPE_CHAR);
    }

    private static String getTaskDescription(String record) {
        return record.substring(START_OF_DESCRIPTION_IN_TASK);
    }
    private static Task createTask(String description, boolean isMarked)
            throws DukeException {
        Task task = Parser.stringToTask(description);
        if (isMarked) {
            task.markAsDone();
        }
        return task;
    }
    private static Deadline createDeadline(String description, String date, boolean isMarked)
            throws DukeException {
        Deadline deadline = Parser.stringToDeadline(description, date);
        if (isMarked) {
            deadline.markAsDone();
        }
        return deadline;
    }
    private static Event createEvent(String description, String date, boolean isMarked)
            throws DukeException{
        Event event = Parser.stringToEvent(description, date);
        if (isMarked) {
            event.markAsDone();
        }
        return event;
    }


    private static String[] getDateAndDescription(String line) {
        String[] dateAndDescription = new String[2];
        int endOfDescriptionIndex = line.indexOf(END_OF_DESCRIPTION_STRING);
        String description = line.substring(START_OF_DESCRIPTION_IN_TASK,
                endOfDescriptionIndex);
        int startDate = line.indexOf(START_OF_DATE_STRING)
                + START_OF_DATE_STRING_OFFSET;
        int endDate = startDate + DATE_LENGTH;
        String date = line.substring(startDate,
                endDate);
        dateAndDescription[DATE] = date;
        dateAndDescription[DESCRIPTION] = description;
        return dateAndDescription;
    }
}
