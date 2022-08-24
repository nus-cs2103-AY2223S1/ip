package duke.storage;

import duke.exception.DukeException;
import duke.exception.IllegalDateFormatException;
import duke.exception.IllegalSyntaxException;
import duke.exception.StorageOperationException;
import duke.parser.Parser;
import duke.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StorageDecoder {

    public static final String DIVIDER = " | ";

    public static final int TASK_TYPE = 0;
    public static final int TASK_STATUS = 1;
    public static final int TASK_DESCRIPTION = 2;
    public static final int TASK_DATE = 3;

    public static TaskList decode(Path path) throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            List<String> decodedString = Files.readAllLines(path);
            for (int i = 0; i < decodedString.size(); i++) {
                String str = decodedString.get(i);
                String[] arr = str.split(DIVIDER);
                Task task = decodeToTask(arr);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error reading from file: " + path);
        }
        return new TaskList(taskList);
    }

    public static Task decodeToTask(String[] decodedStringArr) throws DukeException {
        String taskType = decodedStringArr[TASK_TYPE];
        if (taskType.equals("Todo")) {
            return new Todo(decodedStringArr[TASK_DESCRIPTION], decodedStringArr[TASK_STATUS]);
        } else if (taskType.equals("Deadline")) {
            return new Deadline(decodedStringArr[TASK_DESCRIPTION],
                    Parser.parseToLocalDateTime(decodedStringArr[TASK_DATE]), decodedStringArr[TASK_STATUS]);
        } else if (taskType.equals("Event")) {
            return new Event(decodedStringArr[TASK_DESCRIPTION],
                    Parser.parseToLocalDateTime(decodedStringArr[TASK_DATE]), decodedStringArr[TASK_STATUS]);
        } else {
            throw new IllegalSyntaxException("ERROR!!! Cannot recognise the type of task being saved.");
        }
    }
}
