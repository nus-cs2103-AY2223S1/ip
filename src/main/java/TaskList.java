import java.util.List;
import java.util.ArrayList;

/**
 * List data structure to track all tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

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

    String getIndexOutOfBoundsExceptionMessage() {
        return this.size() > 0 ? String.format("Task index out of range. Please choose from index 1 to %d", this.size()) :
                "Tasks index out of range. There are no tasks.";
    }

    Task deleteTask(int index) throws DukeException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(getIndexOutOfBoundsExceptionMessage());
        }
    }

    Task markTask(int index) throws DukeException {
        try {
            tasks.get(index).markAsDone();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(getIndexOutOfBoundsExceptionMessage());
        }
    }

    Task unmarkTask(int index) throws DukeException {
        try {
            tasks.get(index).unmarkAsDone();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(getIndexOutOfBoundsExceptionMessage());
        }
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

    @Override
    public String toString() {
        return String.join("/n", getAllTasksInDisplayFormat());
    }
}
