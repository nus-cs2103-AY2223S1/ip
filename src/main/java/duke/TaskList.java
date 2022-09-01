package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Datastructure class to store the list of tasks.
 */
public class TaskList {
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
    }

    /**
     * @param i 0-based index of the task to be removed
     * @return Task that was removed from the collection
     */
    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    /**
     * @param i 0 based index of the task to be marked
     * @return toString() of the task
     */
    public String mark(int i) {
        this.tasks.get(i).mark();
        return this.tasks.get(i).toString();
    }

    /**
     * @param i
     * @return
     */
    public String unmark(int i) {
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
