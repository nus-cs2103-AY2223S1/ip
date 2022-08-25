import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayList<Task> getAllTasks() {
        return tasks;
    }

    Task get(int zeroBasedIndex) {
        return tasks.get(zeroBasedIndex);
    }

    void markTaskAsDone(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).markAsDone();
    }

    void markTaskAsUndone(int zeroBasedIndex) {
        tasks.get(zeroBasedIndex).markAsUndone();
    }

    void add(Task task) {
        tasks.add(task);
    }

    void delete(int zeroBasedIndex) throws DukeException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.remove(zeroBasedIndex);
    }

    int size() {
        return tasks.size();
    }
}
