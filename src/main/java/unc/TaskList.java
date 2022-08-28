package unc;

import unc.task.Task;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void markAsNotDone(int index) {
        taskList.get(index).markAsNotDone();
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public TaskList find(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                temp.add(task);
            }
        }
        return new TaskList(temp);
    }
}
