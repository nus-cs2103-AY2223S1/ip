import java.util.List;
import java.util.ArrayList;

/**
 * List data structure to track all tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    int size() {
        return tasks.size();
    }

    Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    Task deleteTask(int index) {
        return tasks.remove(index);
    }

    Task markTask(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    Task unmarkTask(int index) {
        tasks.get(index).unmarkAsDone();
        return tasks.get(index);
    }

    List<String> getAllTasksInDisplayFormat() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
        return numberedTaskList;
    }

    List<String> getAllTasksInStorageFormat() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(tasks.get(i).getStorageFormat());
        }
        return numberedTaskList;
    }

    List<String> exportTasks() {
        List<String> export = new ArrayList<>();
        for (Task task : tasks) {

        }
        return export;
    }

    void importTasks(List<String> tasks) {

    }

    @Override
    public String toString() {
        return String.join("/n", getAllTasksInDisplayFormat());
    }
}
