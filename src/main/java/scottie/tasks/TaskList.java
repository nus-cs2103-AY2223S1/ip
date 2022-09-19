package scottie.tasks;

import java.util.List;

/**
 * API for a list of Tasks.
 */
public interface TaskList extends Iterable<Task> {
    /**
     * Returns whether this TaskList has no Tasks.
     *
     * @return True if this TaskList has no Tasks, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the number of Tasks in this TaskList.
     *
     * @return The number of Tasks in this TaskList.
     */
    int size();

    /**
     * Returns the Task at the given index.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to return.
     * @return The Task at the specified index.
     */
    Task getTask(int index);

    /**
     * Adds the given Task to the TaskList.
     * It is added as the last Task in the list.
     *
     * @param task The Task to add the TaskList.
     */
    void addTask(Task task);

    /**
     * Deletes the Task at the given index from the TaskList.
     * The list is 0-indexed. The indices of the tasks after the given
     * index will be shifted to fill the removed index.
     *
     * @param index The index of the Task to remove from the list.
     */
    void deleteTask(int index);

    /**
     * Returns whether the task at the given index is marked as done.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to check.
     * @return Whether the task with the given index is done.
     */
    boolean isMarked(int index);

    /**
     * Marks the task at the given index as done.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to mark as done.
     */
    void markTask(int index);

    /**
     * Marks the task at the given index as not done.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to mark as not done.
     */
    void unmarkTask(int index);

    /**
     * Returns a List of Tasks in this TaskList whose
     * descriptions contain the given searchText.
     * This search is case-insensitive.
     *
     * @param searchText The text to search for.
     * @return A List of matching Tasks.
     */
    List<Task> filterTasks(String searchText);

    /**
     * Sorts the Tasks in this TaskList by their descriptions.
     * If the isReversed argument is true, sort them in the reversed order.
     *
     * @param isReversed Whether to sort in the reversed order.
     */
    void sortByDescription(boolean isReversed);

    /**
     * Sorts the Tasks in this TaskList by their descriptions.
     * If the isReversed argument is true, sort them in the reversed order.
     * Tasks without a date are always sorted behind those with dates.
     *
     * @param isReversed Whether to sort in the reversed order.
     */
    void sortByDate(boolean isReversed);
}
