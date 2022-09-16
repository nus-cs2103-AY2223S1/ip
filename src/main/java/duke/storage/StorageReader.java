package duke.storage;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;

public class StorageReader {

    /**
     * Reads the file lines in the save file and returns the task represented by that line.
     *
     * @param fileLine Line in save file to read.
     * @return Task represented by fileLine.
     */
    public static Task fileLineToTask(String fileLine) {
        String[] strings = parseFileLine(fileLine);

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

    /**
     * Parses a file line.
     *
     * @param fileLine Line in save file to read.
     * @return Array of string representing the parsed fileLine.
     */
    public static String[] parseFileLine(String fileLine) {
        String delimiter = " \\| ";
        return fileLine.split(delimiter, 4);
    }

    /**
     * Returns the status of the task.
     * That is, done or not done.
     *
     * @param strings Parsed file line.
     * @return True if task is marked as done, false otherwise.
     */
    public static boolean isDone(String[] strings) {
        return strings[1].equals("1");
    }

    /**
     * Returns a one letter representation of the task.
     *
     * @param strings Parsed file line.
     * @return T, D, or E, depending on what task type the file line represents.
     */
    public static String getTaskHead(String[] strings) {
        return strings[0];
    }

    /**
     * Returns the description of the task.
     *
     * @param strings Parsed file line.
     * @return Description of task represented by the file line.
     */
    public static String getDescription(String[] strings) {
        return strings[2];
    }

    /**
     * Returns the date of the task
     *
     * @param strings Parsed file line.
     * @return LocalDate of the task represented by the file line.
     */
    public static LocalDate getDate(String[] strings) {
        return LocalDate.parse(strings[3]);
    }
}
