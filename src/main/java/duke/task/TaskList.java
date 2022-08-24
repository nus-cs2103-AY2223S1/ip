package duke.task;

import duke.DukeException;

import java.util.List;
import java.util.ArrayList;

/**
 * List data structure to track all tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    String getIndexOutOfBoundsExceptionMessage() {
        return this.size() > 0 ? String.format("Task index out of range. Please choose from index 1 to %d", this.size()) :
                "Tasks index out of range. There are no tasks.";
    }

    Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(this.getIndexOutOfBoundsExceptionMessage());
        }
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(this.getIndexOutOfBoundsExceptionMessage());
        }
    }

    public Task markTask(int index) throws DukeException {
        this.getTask(index).markAsDone();
        return this.getTask(index);
    }

    public Task unmarkTask(int index) throws DukeException {
        this.getTask(index).unmarkAsDone();
        return this.getTask(index);
    }

    public List<String> getAllTasksInDisplayFormat() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
        return numberedTaskList;
    }

    public List<String> getAllTasksInStorageFormat() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(tasks.get(i).getStorageFormat());
        }
        return numberedTaskList;
    }

    public TaskList find(String keyword) {
        TaskList tasksMatched = new TaskList();
        for (Task task : this.tasks) {
            if (task.contains(keyword)) {
                tasksMatched.addTask(task);
            }
        }
        return tasksMatched;
    }

    @Override
    public String toString() {
        return String.join("/n", getAllTasksInDisplayFormat());
    }
}
