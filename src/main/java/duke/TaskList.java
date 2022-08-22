package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task markTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = this.tasks.remove(index);
        return task;
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Returns a TaskList with a specified keyword.
     *
     * @param keyword The string to be checked.
     * @return A TaskList containing the tasks that match with the keyword.
     */
    public TaskList findMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.match(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            result.append(String.format("%d. %s\n", 1 + i, this.tasks.get(i).toString()));
        }
        return result.toString();
    }
}
