package duke.storage;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A StorageEncoder class that encapsulates the action of encoding the TaskList object into the storage file.
 */
public class StorageEncoder {

    private static final String DIVIDER = " | ";

    /**
     * Encodes the given TaskList Object into a List of String.
     *
     * @param taskList the TaskList object
     * @return the list of String
     */
    public static List<String> encode(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        List<String> res = new ArrayList<>();
        for (Task task : tasks) {
            int status = task.isDone() ? 1 : 0;
            String str = (task.getTaskType()
                        + DIVIDER
                        + status
                        + DIVIDER
                        + task.getDescription()
                        + DIVIDER
                        + task.getDate());
            res.add(str);
        }
        return res;
    }


}
