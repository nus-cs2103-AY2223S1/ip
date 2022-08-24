package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class StorageEncoder {

    public static final String DIVIDER = " | ";

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
