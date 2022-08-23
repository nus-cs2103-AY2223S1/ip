package models;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tl) {
        taskList = tl;
    }

    public void AddTask(Task task) {
        taskList.add(task);
    }

    public Task DeleteTask(int taskIndex) {
        return taskList.remove(taskIndex);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }
}
