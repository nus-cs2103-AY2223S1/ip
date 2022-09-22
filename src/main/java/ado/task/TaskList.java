package ado.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains task list and operations to modify tasks
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void markTaskAtIndex(int index) {
        list.get(index).markAsDone();
    }

    public void unmarkTaskAtIndex(int index) {
        list.get(index).markAsUndone();
    }

    public void removeTaskAtIndex(int index) {
        list.remove(index);
    }

    public Task getTaskAtIndex(int index) {
        return list.get(index);
    }

    public int listSize() {
        return list.size();
    }

    public List<Task> getList() {
        return list;
    }
}
