package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task markTask(int index) throws DukeException {
        if (index < 0 || index >= size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        return tasks.remove(index);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s\n", 1 + i, tasks.get(i).toString()));
        }
        return result.toString();
    }
}
