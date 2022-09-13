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
        char type = line.charAt(INDEX_OF_TYPE_CHAR);
        boolean isMarked = line.charAt(MARKED_STATUS) != UNMARKED_SYMBOL;
        String[] dateAndDescription;
        LocalDate date;
        Task t;
        switch(type) {
        case 'T':
            t = Parser.stringToTask(line.substring(START_OF_DESCRIPTION_IN_TASK));
            if (isMarked) {
                t.markAsDone();
            }
            return t;
        case 'D':
            dateAndDescription = getDateAndDescription(line);
            t = Parser.stringToDeadline(dateAndDescription[DESCRIPTION], dateAndDescription[DATE]);
            if (isMarked) {
                t.markAsDone();
            }
            return t;
        case 'E':
            dateAndDescription = getDateAndDescription(line);
            date = LocalDate.parse(dateAndDescription[DATE]);
            t = new Event(dateAndDescription[DESCRIPTION], date);
            if (isMarked) {
                t.markAsDone();
            }
            return t;
        default:
            return null;
        }

    }


    private static String[] getDateAndDescription(String line) {
        String[] dateAndDescription = new String[2];
        int endOfDescriptionIndex = line.indexOf(END_OF_DESCRIPTION_STRING);
        String description = line.substring(START_OF_DESCRIPTION_IN_TASK,
                endOfDescriptionIndex);
        int startDate = line.indexOf(START_OF_DATE_STRING)
                + START_OF_DATE_STRING_OFFSET;
        String date = line.substring(startDate,
                startDate + DATE_LENGTH);
        dateAndDescription[DATE] = date;
        dateAndDescription[DESCRIPTION] = description;
        return dateAndDescription;
    }


}
