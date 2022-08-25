package duke.main;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task get(int zeroBasedIndex) {
        return tasks.get(zeroBasedIndex);
    }

    public void markTaskAsDone(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).markAsDone();
    }

    public void markTaskAsUndone(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).markAsUndone();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int zeroBasedIndex) throws DukeException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.remove(zeroBasedIndex);
    }

    public int size() {
        return tasks.size();
    }
}
