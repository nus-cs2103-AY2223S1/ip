package duke.task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int num) {
        this.tasks.remove(num);
    }

    public void unMarkTask(int num) {
        this.tasks.get(num).markDone();
    }

    public void markTask(int num) {
        this.tasks.get(num).unMarkDone();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int num) {
        return this.tasks.get(num);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public ArrayList<Task> filterTasks(String keyWord) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyWord)) {
                result.add(t);
            }
        }
        return result;
    }
}
