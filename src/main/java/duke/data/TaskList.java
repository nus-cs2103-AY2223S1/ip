package duke.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import duke.data.tasks.Task;

public class TaskList implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int taskIdx) {
        return this.tasks.get(taskIdx);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskIdx) {
        Task deletedTask = this.getTask(taskIdx);
        this.tasks.remove(taskIdx);
        return deletedTask;
    }

    public TaskList findTasks(String keyword) {
        return new TaskList(this.tasks.stream().filter(task -> task.containsKeyword(keyword))
            .collect(java.util.stream.Collectors.toList()));
    }

    @Override
    public String toString() {
        List<String> taskListStr = new ArrayList<>();
        for (int taskIdx = 0; taskIdx < tasks.size(); taskIdx++) {
            taskListStr.add(String.format("%d. %s", taskIdx + 1, tasks.get(taskIdx)));
        }

        return String.join("\n", taskListStr);
    }
}
