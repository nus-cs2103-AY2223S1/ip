package duke.task;

import duke.DukeException;
import duke.Storage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public class TaskList extends ArrayList<Task> {
    private final Storage storage;

    public TaskList() {
        this(null);
    }
    public TaskList(Storage storage) {
        super(100);
        if (storage != null) {
            try {
                this.addAll(storage.loadTasks());
            } catch (DukeException e) {
                // initialize with no tasks.
            }
        }
        this.storage = storage;
    }

    public ArrayList<Task> filterByKeyword(String keyword) {
        TaskList filtered = new TaskList();
        for (Task task : this) {
            if (task.matches(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }


    public boolean addTask(Task task) throws DukeException {
        if (super.add(task)) {
            if (storage != null) {
                storage.saveTasks(this);
            }
            return true;
        }
        return false;
    }

    public Task removeTask(int index) throws DukeException {
        if (0 <= index && index <= this.size()) {
            Task task = super.remove(index);
            storage.saveTasks(this);
            return task;
        } else {
            throw new DukeException("Failed to delete task %d", index + 1);
        }
    }

    public Task setCompletion(int index, boolean completed) throws DukeException {
        try {
            Task task = super.get(index);
            task.setDone(completed);
            if (storage != null) storage.saveTasks(this);
            return task;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Task %d not found.", index + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        IntStream.range(0, this.size()).forEach(i -> output.append(String.format("\t%d. %s%n", i + 1, this.get(i))));
        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskList)) return false;
        if (!super.equals(o)) return false;
        TaskList tasks = (TaskList) o;
        return Objects.equals(storage, tasks.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), storage);
    }
}
