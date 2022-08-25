package scottie.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Iterable<Task> {
    private final Storage storage;
    private final List<Task> tasks;

    public TaskList() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>();
        for (String taskData : this.storage.loadTasksData()) {
            try {
                this.addTask(Task.fromEncodedString(taskData));
            } catch (InvalidTaskDataException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.saveTasks();

    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.saveTasks();
    }

    public void markTask(int index) {
        this.getTask(index).markAsDone();
        this.saveTasks();
    }

    public void unmarkTask(int index) {
        this.getTask(index).markAsUndone();
        this.saveTasks();
    }

    /**
     * Returns a List of Tasks in this TaskList whose
     * descriptions contain the given searchText.
     * This search is case-insensitive.
     *
     * @param searchText The text to search for.
     * @return A List of matching Tasks.
     */
    public List<Task> filterTasks(String searchText) {
        return this.tasks.stream()
                .filter(task -> task.matchesAgainst(searchText))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    private void saveTasks() {
        this.storage.saveTasks(this.tasks);
    }
}
