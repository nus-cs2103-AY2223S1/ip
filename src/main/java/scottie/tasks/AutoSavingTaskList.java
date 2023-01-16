package scottie.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import scottie.storage.Storage;

/**
 * A TaskList equipped with a Storage.
 * The tasks are initially loaded from the Storage and any changes
 * to the list are always immediately saved to the Storage.
 */
public class AutoSavingTaskList implements TaskList {
    private final Storage storage;
    private final List<Task> tasks;

    /**
     * Constructs an AutoSavingTaskList.
     *
     * @param storage The Storage to use for loading and saving data.
     */
    public AutoSavingTaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        for (String taskData : this.storage.loadData()) {
            try {
                this.addTask(Task.fromEncodedString(taskData));
            } catch (InvalidTaskDataException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.tasks.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTask(Task task) {
        this.tasks.add(task);
        this.saveTasks();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.saveTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMarked(int index) {
        return this.tasks.get(index).isMarked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markTask(int index) {
        this.getTask(index).markAsDone();
        this.saveTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unmarkTask(int index) {
        this.getTask(index).markAsUndone();
        this.saveTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> filterTasks(String searchText) {
        return this.tasks.stream()
                .filter(task -> task.matchesAgainst(searchText))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sortByDescription(boolean isReversed) {
        Comparator<Task> comparator = Comparator.comparing(Task::getDescription);
        if (isReversed) {
            comparator = comparator.reversed();
        }
        this.tasks.sort(comparator);
        this.saveTasks();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sortByDate(boolean isReversed) {
        Comparator<LocalDateTime> localDateTimeComparator =
                isReversed ? Comparator.reverseOrder() : Comparator.naturalOrder();
        this.tasks.sort(Comparator.comparing(Task::getDateTime,
                Comparator.nullsLast(localDateTimeComparator)));
        this.saveTasks();
    }

    /**
     * Saves the tasks in this list to the storage.
     * This method is called whenever the tasks in the list
     * are modified.
     */
    private void saveTasks() {
        List<String> encodedTasks = this.tasks.stream().map(Task::toEncodedString).collect(Collectors.toList());
        this.storage.saveData(encodedTasks);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
