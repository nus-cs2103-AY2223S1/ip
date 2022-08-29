package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.FileIoException;
import duke.exception.IllegalSyntaxException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A StorageDecoder class that encapsulates that action of decoding storage file
 */
public class StorageDecoder {

    private static final String DIVIDER = " | ";

    private static final int TASK_TYPE = 0;
    private static final int TASK_STATUS = 2;
    private static final int TASK_DESCRIPTION = 4;
    private static final int TASK_DATE = 6;

    /**
     * Decodes the storage file into a TaskList object
     *
     * @param path the place where the storage file is at
     * @return the TaskList object
     * @throws DukeException throws an Exception when encountering unexpected behaviour.
     */
    public static TaskList decode(Path path) throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            List<String> decodedString = Files.readAllLines(path);
            for (String str : decodedString) {
                String[] arr = str.trim().split(DIVIDER);
                Task task = decodeToTask(arr);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new FileIoException("Error reading from file: " + path);
        }
        return new TaskList(taskList);
    }

    /**
     * Decodes a String into a Task object
     *
     * @param decodedStringArr decoded string array from the storage file
     * @return a Task object
     * @throws DukeException throws an Exception when encountering unexpected behaviour.
     */
    public static Task decodeToTask(String[] decodedStringArr) throws DukeException {
        String taskType = decodedStringArr[TASK_TYPE];
        switch (taskType) {
        case "Todo":
            return new Todo(decodedStringArr[TASK_DESCRIPTION], decodedStringArr[TASK_STATUS]);
        case "Deadline":
            return new Deadline(decodedStringArr[TASK_DESCRIPTION],
                    Parser.parseToLocalDateTime(decodedStringArr[TASK_DATE]), decodedStringArr[TASK_STATUS]);
        case "Event":
            return new Event(decodedStringArr[TASK_DESCRIPTION],
                    Parser.parseToLocalDateTime(decodedStringArr[TASK_DATE]), decodedStringArr[TASK_STATUS]);
        default:
            throw new IllegalSyntaxException("ERROR!!! Cannot recognise the type of task being saved.");
        }
    }
}
