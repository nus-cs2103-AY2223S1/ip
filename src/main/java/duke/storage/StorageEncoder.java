package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * A StorageEncoder class that encapsulates the action of encoding the TaskList object into the storage file.
 */
public class StorageEncoder {

    static final String DIVIDER = " | ";

    /**
     * Encodes the given TaskList Object into a List of String.
     * @param taskList the TaskList object.
     * @return the list of String.
     */
    public static List<String> encode(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            Integer status = task.isDone() ? 1 : 0;
            String str = (task.getTaskType() + DIVIDER + status + DIVIDER + task.getDescription() + DIVIDER + task.getDate());
            res.add(str);
        }
        return res;
    }


}
