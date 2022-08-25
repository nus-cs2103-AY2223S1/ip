package tasks;

import java.util.ArrayList;
import java.util.List;

enum SortType {
    name,
    time,
    status,
}

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
}
