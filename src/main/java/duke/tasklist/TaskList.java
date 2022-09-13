package duke.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.Storage;
import duke.task.Task;

/**
 * Represents a {@code List} of the {@code Tasks}
 */
public class TaskList {
    private final List<Task> userTasks = new ArrayList<>();
    private final Storage storage;

    /**
     * Reads an existing file if it exists, else uses an empty {@code List}.
     * @param storage A {@code Storage} instance to take in
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        TaskListSerialiser.deserialiseStorageString(this, storage);
    }

    /**
     * Returns the number of {@code Tasks}
     * @return The number of {@code Tasks}
     */
    public int sizeOfList() {
        return userTasks.size();
    }

    private void saveToStorage() {
        storage.writeToDisk(TaskListSerialiser.serialiseTaskListForStorage(userTasks));
    }

    /**
     * Adds a new {@code Task}
     * @param newTask The {@code Task} to be added
     */
    public void addToTaskList(Task newTask) {
        int length = userTasks.size();
        userTasks.add(newTask);
        assert userTasks.size() == length + 1 : "The length of the tasks list has not increased";
        saveToStorage();
    }

    /**
     * Marks as the {@code Task} as done.
     * @param index The index of the {@code Task}
     */
    public void markTaskAsDone(int index) {
        userTasks.get(index).setTaskAsDone();
        saveToStorage();
    }

    /**
     * Marks as the {@code Task} as not done.
     * @param index The index of the {@code Task}
     */
    public void markTaskAsNotDone(int index) {
        userTasks.get(index).setTaskAsNotDone();
        saveToStorage();
    }

    /**
     * Deletes the specified {@code Task}
     * @param index The index of the {@code Task}
     */
    public void removeTask(int index) {
        int length = userTasks.size();
        userTasks.remove(index);
        assert userTasks.size() == length - 1 : "The length of the tasks list has not decreased";
        saveToStorage();
    }

    /**
     * Gets a {@code String} representation of the {@code Task}
     * @param index The index of the {@code Task}
     * @return A {@code String} representation
     */
    public String getTaskString(int index) {
        return userTasks.get(index).toString();
    }

    /**
     * Returns the textual representation of the {@code Tasks} matching the keyword.
     * @param keyword The keyword to search for
     * @return The {@code String} representation of the {@code Tasks}
     */
    public String getTextRepresentationOfKeywordTasks(String keyword) {
        return TaskListSerialiser.serialiseTaskListForDisplay(userTasks.stream()
                .filter(task -> task.isMatchingKeywordInDescription(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Returns the textual representation of all {@code Tasks}.
     * @return The {@code String} representation of the {@code Tasks}
     */
    public String getTextRepresentationOfAllTasks() {
        return TaskListSerialiser.serialiseTaskListForDisplay(userTasks);
    }
}
