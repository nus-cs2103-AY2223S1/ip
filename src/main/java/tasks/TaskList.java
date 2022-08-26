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
        return this.taskList.get(index);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void mark(int index) {
        this.taskList.get(index).mark();
    }

    public void unmark(int index) {
        this.taskList.get(index).unmark();
    }
}
