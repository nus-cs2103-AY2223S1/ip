package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Datastructure class to store the list of tasks.
 */
public class TaskList {
    private static final DukeException INVALID_INDEX_EXCEPTION = new DukeException("INVALID INDEX");
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @param tasks
     * @throws DukeException
     */
    public TaskList(List<Task> tasks) throws DukeException {

        this.tasks = tasks;
        if (tasks == null) {
            throw new DukeException("task passed to tasklist is null");
        }
    }

    /**
     * @param task Task to be added into the collection of tasks
     */
    public void add(Task task) {
        this.tasks.add(task);
        this.sort();
    }

    /**
     * @param i 0-based index of the task to be removed
     * @return Task that was removed from the collection
     */
    public Task remove(int i) throws DukeException {
        if (i >= tasks.size() || i < 0) {
            throw INVALID_INDEX_EXCEPTION;
        }
        return this.tasks.remove(i);
    }

    /**
     * @param i 0 based index of the task to be marked
     * @return toString() of the task
     */
    public String mark(int i) throws DukeException {
        if (i >= tasks.size() || i < 0) {
            throw INVALID_INDEX_EXCEPTION;
        }
        this.tasks.get(i).mark();
        return this.tasks.get(i).toString();
    }

    /**
     * @param i
     * @return
     */
    public String unmark(int i) throws DukeException {
        if (i >= tasks.size() || i < 0) {
            throw INVALID_INDEX_EXCEPTION;
        }
        this.tasks.get(i).unMark();
        return this.tasks.get(i).toString();
    }

    /**
     * @param word Word to find in the list of tasks
     * @return String of the filtered tasks
     */
    public String find(String word) {
        String filtered = tasks.stream()
                .filter(task ->
                        task.description
                                .toLowerCase()
                                .contains(word.toLowerCase()))
                .map(task -> task.toString())
                .collect(Collectors.joining("\n"));
        return filtered;
    }

    /**
     * Sorts the list of tasks
     */
    public void sort() {
        tasks.sort((t1, t2) -> {
            if (t1 instanceof Todo && t2 instanceof Todo) {
                return t1.compareTo(t2);
            } else if (t1 instanceof Todo) {
                return -1;
            } else if (t2 instanceof Todo) {
                return 1;
            } else {
                return t1.compareTo(t2);
            }
        });
    }

    /**
     * @return list of tasks
     */
    public String toString() {
        String out = "";
        for (Task t : tasks) {
            out += t.toFileString() + "\n";
        }
        return out;
    }
}
