package duke.utils;

import duke.exceptions.EmptyTaskDescException;
import duke.exceptions.EmptyTaskDateException;
import duke.exceptions.NoSuchTaskTypeException;
import duke.exceptions.UnrecognisedDateException;
import duke.tasks.*;

public class TaskParser {

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
