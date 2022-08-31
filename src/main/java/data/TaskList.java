package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task markDone(int idx) {
        Task task = tasks.get(idx);
        task.markDone();
        return task;
    }

    public Task delete(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public String[] findAndReturnStringArr(String searchString) {
        List<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.contains(searchString)) {
                results.add(t);
            }
        }

        String[] outputStrings = new String[results.size() + 1];
        outputStrings[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < results.size(); i++) {
            outputStrings[i + 1] = (i + 1) + ". " + results.get(i).toString();
        }
        return outputStrings;
    }

    public int size() {
        return tasks.size();
    }

    public String[] toStringArr() {
        String[] taskStrings = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
        }
        return taskStrings;
    }
}
