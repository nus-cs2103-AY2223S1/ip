package duke.utils;

import duke.exceptions.EmptyTaskDateException;
import duke.exceptions.EmptyTaskDescException;
import duke.exceptions.NoSuchTaskTypeException;
import duke.exceptions.UnrecognisedDateException;
import duke.tasks.*;

public class TaskParser {

    /**
     * Parses a String from the input containing task information into a Task.
     * @param type Type of the Task.
     * @param s String containing task information.
     * @return Task object.
     * @throws EmptyTaskDateException If a date is required but not specified.
     * @throws EmptyTaskDescException If no description is specified.
     * @throws NoSuchTaskTypeException If no such task type exists.
     * @throws UnrecognisedDateException If the format of the given date is unrecognised.
     */
    public static Task stringToTask(TaskType type, String s)
            throws EmptyTaskDateException, EmptyTaskDescException, NoSuchTaskTypeException, UnrecognisedDateException {
        if ("".equals(s)) {
            throw new EmptyTaskDescException();
        }

        String[] taskInfo;

        switch (type) {
        case TODO:
            return new Todo(s);
        case EVENT:
            taskInfo = s.split("/at");
            if (taskInfo.length < 2) {
                throw new EmptyTaskDateException();
            }
            return new Event(taskInfo[0].trim(), DateParser.stringToDate(taskInfo[1].trim()));
        case DEADLINE:
            taskInfo = s.split("/by");
            if (taskInfo.length < 2) {
                throw new EmptyTaskDateException();
            }
            return new Deadline(taskInfo[0].trim(), DateParser.stringToDate(taskInfo[1].trim()));
        default:
            throw new NoSuchTaskTypeException(type.name());
        }
    }

}
