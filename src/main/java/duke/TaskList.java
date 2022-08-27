package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> persistedTasks) {
        this.tasks = persistedTasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public String find(String keyword) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.descriptionContains(keyword)) {
                result += (i + 1) + "." + task;
                if (i != tasks.size() - 1) {
                    result += "\n";
                }
            }
        }
        return result;
    }

    public String delete(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        return task.toString();
    }

    public String markAsDone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsDone();
        return task.toString();
    }

    public String markAsUndone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsUndone();
        return task.toString();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks currently.";
        }
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
