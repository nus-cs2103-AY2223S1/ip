package Duke.storage;

import Duke.tasks.Task;
import Duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Encode the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {

    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTaskList = new ArrayList<>();
        for (Task task : toSave.getAllTasks()) {
            encodedTaskList.add(encodeTaskToString(task));
        }
        return encodedTaskList;
    }

    public static String encodeTaskToString(Task task) {
        return task.getTaskType() + "|"
                + (task.isCompleted() ? 1 : 0) + "|"
                + task.getDesc();
    }
}
