package Duke.storage;

import Duke.tasks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Decodes the storage data file into a {@code TaskList} object.
 */
public class TaskListDecoder {

    /**
     * Decodes {@code encodedTaskList} into a {@code TaskList} containing the decoded tasks.
     */
    public static ArrayList<Task> decodeTaskList(List<String> encodedTaskList) {
        ArrayList<Task> decodedTaskList = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTaskList.add(decodeTaskFromString(encodedTask));
        }
        decodedTaskList.remove(null);
        return decodedTaskList;
    }

    public static Task decodeTaskFromString(String encodedTask) {
        Task result = null;
        String[] str = encodedTask.split("\\|");
        switch (str[0]) {
            case "T":
                Todo todo = new Todo(str[2]);
                if (Objects.equals(str[1], "1")) {
                    todo.mark();
                }
                result = todo;
                break;
            case "D":
                Deadline deadline = new Deadline(str[2], str[3]);
                if (Objects.equals(str[1], "1")) {
                    deadline.mark();
                }
                result = deadline;
                break;
            case "E":
                Event event = new Event(str[2], str[3]);
                if (Objects.equals(str[1], "1")) {
                    event.mark();
                }
                result = event;
                break;
        }
        return result;
    }
}
