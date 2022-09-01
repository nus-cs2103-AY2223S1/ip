package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Encode the {@code TaskList} object into a data file for storage.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class TaskListEncoder {

    /**
     * Encodes a task list into a List<String> containing the decoded tasks.
     *
     * @param toEncode the task list to be encoded
     *
     * @return the encoded task list
     */
    public static List<String> encodeTaskList(TaskList toEncode) {
        final List<String> encodedTaskList = new ArrayList<>();
        for (Task task : toEncode.getAllTasks()) {
            encodedTaskList.add(encodeTaskToString(task));
        }
        return encodedTaskList;
    }

    /**
     * Encodes the given task into a template format to be stored.
     *
     * @param task the task to be encoded
     *
     * @return a String representing the encoded task
     */
    public static String encodeTaskToString(Task task) {
        return task.getTaskType() + "|"
                + (task.isCompleted() ? 1 : 0) + "|"
                + task.getDesc();
    }
}
