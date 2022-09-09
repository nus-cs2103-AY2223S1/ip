package duke.storage;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;

public class StorageReader {

    public static Task fileLineToTask(String fileLine) {
        String[] strings = splitFileLine(fileLine);

        boolean isDone = isDone(strings);
        String taskHead = getTaskHead(strings);
        String description = getDescription(strings);

        Task res;
        switch(taskHead) {
        case "T":
            res = new ToDo(description, isDone);
            break;
        case "D":
            res = new Deadline(description, isDone, getDate(strings));
            break;
        case "E":
            res = new Event(description, isDone, getDate(strings));
            break;
        default:
            throw new DukeException("Failed to load task from file!");
        }

        return res;
    }

    public static String[] splitFileLine(String fileLine) {
        String delimiter = " \\| ";
        return fileLine.split(delimiter, 4);
    }

    public static boolean isDone(String[] strings) {
        return strings[1].equals("1");
    }

    public static String getTaskHead(String[] strings) {
        return strings[0];
    }

    public static String getDescription(String[] strings) {
        return strings[2];
    }

    public static LocalDate getDate(String[] strings) {
        return LocalDate.parse(strings[3]);
    }
}
