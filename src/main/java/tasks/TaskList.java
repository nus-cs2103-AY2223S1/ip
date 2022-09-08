package tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public Task retrieveTask(int index) {
        assert index >= 0: "index should be greater than or equal to 0";
        return this.taskList.get(index);
    }

    public void deleteTask(int index) {
        assert index >= 0: "index should be greater than or equal to 0";
        this.taskList.remove(index);
    }

    public int getSize() {
        assert this.taskList.size() >= 0: "size should be greater than or equal to 0";
        return this.taskList.size();
    }

    public void mark(int index) {
        assert index >= 0: "index should be greater than or equal to 0";
        this.taskList.get(index).mark();
    }

    public void unmark(int index) {
        assert index >= 0: "index should be greater than or equal to 0";
        this.taskList.get(index).unmark();
    }
}
