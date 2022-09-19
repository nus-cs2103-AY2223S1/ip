package pony;

import pony.PonyException;
import pony.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) throws PonyException {
        if (tasks.contains(task)) {
            throw new PonyException("You have already recorded this task!!");
        }
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }
    public int sizeOf() {
        return this.tasks.size();
    }

    public Task getTask(int index) throws PonyException {
        // Check if index is valid
        if (index < 0 || index >= sizeOf()) {
            throw new PonyException("Task index invalid!!");
        }
        assert index < sizeOf() && index >= 0 : "Task index should be valid";
        return tasks.get(index);
    }

    public int getTasksCount() {
        return tasks.size();
    }


}
