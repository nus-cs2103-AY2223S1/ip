package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

import duke.DukeException;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
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
            throw new DukeException("Index out of bound");
        }
        return tasks.get(index);
    }

    public void remove(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bound");
        }
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
