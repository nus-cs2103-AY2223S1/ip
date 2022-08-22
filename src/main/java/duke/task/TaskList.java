package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("List of tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(String.format("\t%d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bounds");
        }
        return tasks.get(index);
    }

    public void remove(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bounds");
        }
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
