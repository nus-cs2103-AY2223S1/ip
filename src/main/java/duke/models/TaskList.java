package duke.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The TaskList class is a wrapper around a List of Task objects.
 *
 * @author Zhu Yuanxi
 */
public class TaskList {
    public static List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tl) {
        taskList = tl;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return taskList.remove(taskIndex);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void forEach(Consumer<Task> action) {
        for(Task task : taskList) {
            action.accept(task);
        }
    }
}
