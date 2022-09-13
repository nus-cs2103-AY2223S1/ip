package duke.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents a {@code List} of the {@code Tasks}
 */
public class TaskList {
    private final List<Task> userTasks = new ArrayList<>();

    /**
     * Returns the number of {@code Tasks}
     *
     * @return The number of {@code Tasks}
     */
    public int sizeOfList() {
        return userTasks.size();
    }

    /**
     * Adds a new {@code Task}
     *
     * @param newTask The {@code Task} to be added
     */
    public void addToTaskList(Task newTask) {
        int length = userTasks.size();
        userTasks.add(newTask);
        assert userTasks.size() == length + 1 : "The length of the tasks list has not increased";
    }

    /**
     * Marks as the {@code Task} as done.
     *
     * @param index The index of the {@code Task}
     */
    public void markTaskAsDone(int index) {
        userTasks.get(index).setTaskAsDone();
    }

    /**
     * Marks as the {@code Task} as not done.
     *
     * @param index The index of the {@code Task}
     */
    public void markTaskAsNotDone(int index) {
        userTasks.get(index).setTaskAsNotDone();
    }

    /**
     * Deletes the specified {@code Task}
     *
     * @param index The index of the {@code Task}
     */
    public void removeTask(int index) {
        int length = userTasks.size();
        userTasks.remove(index);
        assert userTasks.size() == length - 1 : "The length of the tasks list has not decreased";
    }

    /**
     * Deletes all the specified {@code Tasks}
     */
    public void removeAllTasks() {
        userTasks.clear();
    }

    /**
     * Gets a {@code String} representation of the {@code Task}
     *
     * @param index The index of the {@code Task}
     * @return A {@code String} representation
     */
    public String getTaskString(int index) {
        return userTasks.get(index).toString();
    }

    /**
     * Returns the textual representation of the {@code Tasks} matching the keyword.
     *
     * @param keyword The keyword to search for
     * @return The {@code String} representation of the {@code Tasks}
     */
    public String getTextRepresentationOfTasksMatchingKeyword(String keyword) {
        return TaskListSerialiser.serialiseTaskListForDisplay(userTasks.stream()
                .filter(task -> task.isMatchingKeywordInDescription(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Returns the textual representation of all {@code Tasks} for display.
     *
     * @return The {@code String} representation of the {@code Tasks} for display
     */
    public String getTextRepresentationOfAllTasksForDisplay() {
        return TaskListSerialiser.serialiseTaskListForDisplay(userTasks);
    }

    /**
     * Returns the textual representation of all {@code Tasks} for storage.
     *
     * @return The {@code String} representation of the {@code Tasks} for storage
     */
    public String getTextRepresentationOfAllTasksForStorage() {
        return TaskListSerialiser.serialiseTaskListForStorage(userTasks);
    }
}
