package duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Decodes the storage data file into a {@code TaskList} object.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class TaskListDecoder {

    /**
     * Decodes a List<String> into a TaskList containing the decoded tasks.
     *
     * @param encodedTaskList the task list to be decoded
     *
     * @return the decoded task list
     */
    public static ArrayList<Task> decodeTaskList(List<String> encodedTaskList) {
        ArrayList<Task> decodedTaskList = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTaskList.add(decodeTaskFromString(encodedTask));
        }
        decodedTaskList.remove(null);
        return decodedTaskList;
    }

    /**
     * Decodes the given String into a task.
     *
     * @param encodedTask the String to be decoded
     *
     * @return the task represented by the encoded String
     */
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
        default:
            try {
                throw new DukeException("     OOPS!!! Unrecognised task");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
