package duke.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import duke.command.SortCommand;
import duke.enums.SortMetric;
import duke.enums.SortOrder;
import duke.exception.InvalidCommandFormatException;
import duke.exception.TaskIndexOutOfBoundsException;
/**
 * Represents the list of tasks added by the user.
 *
 * @author njxue
 * @version v0.1
 */
public class TaskList {
    /** List of tasks. */
    private List<Task> tasks;
    /** Order of the tasks to sort by. */
    private SortOrder sortOrder = SortOrder.ASCENDING;
    /** Measurement used to determine the order of the tasks. */
    private SortMetric sortMetric = SortMetric.DEADLINE;

    /**
     * Creates a TaskList object from a List of tasks.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a TaskList object containing an empty List of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a Task object to the List of tasks.
     *
     * @param task Task object to be added.
     */
    public void add(Task task) {
        assert(task != null);
        tasks.add(task);
    }

    /**
     * Removes the Task object at index taskIndex from the List of tasks.
     *
     * @param taskIndex Index of the task in the List of tasks.
     * @return The deleted Task object.
     * @throws TaskIndexOutOfBoundsException If the taskIndex is not within range of the List of tasks.
     */
    public Task delete(int taskIndex) throws TaskIndexOutOfBoundsException {
        checkIsValidIndex(taskIndex);
        Task task = this.tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        return task;
    }

    /**
     * Marks the Task object at index taskIndex in the List of tasks as completed.
     *
     * @param taskIndex Index of the task in the List of tasks.
     * @return The Task object marked as completed.
     * @throws TaskIndexOutOfBoundsException If the taskIndex is not within range of the List of tasks.
     */
    public Task mark(int taskIndex) throws TaskIndexOutOfBoundsException {
        checkIsValidIndex(taskIndex);
        assert(taskIndex - 1 >= 0 && taskIndex - 1 < getSize());
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks the Task object at index taskIndex in the List of tasks as completed.
     * In other words, marks the Task object as incomplete.
     *
     * @param taskIndex Index of the task in the List of tasks.
     * @return The Task object unmarked as completed.
     * @throws TaskIndexOutOfBoundsException If the taskIndex is not within range of the List of tasks.
     */
    public Task unmark(int taskIndex) throws TaskIndexOutOfBoundsException {
        checkIsValidIndex(taskIndex);
        assert(taskIndex - 1 >= 0 && taskIndex - 1 < getSize());
        Task task = tasks.get(taskIndex - 1);
        task.unmarkAsDone();
        return task;
    }

    /**
     * Sorts the list of tasks in order of ascending date and time.
     */
    public void sort() throws InvalidCommandFormatException {
        switch (sortMetric) {
        case DEADLINE:
            // Sort by the task deadline or event time.
            tasks.sort(Comparator.comparing(Task::getDateTime));
            break;
        case DATE_ADDED:
            // Sort by the date the tasks were added.
            tasks.sort(Comparator.comparing(Task::getDateAdded));
            break;
        case TYPE:
            // Sort by task types.
            tasks.sort(Comparator.comparing(Task::toString));
            break;
        case DESCRIPTION:
            // Sort by task description.
            tasks.sort(Comparator.comparing(Task::getDescription));
            break;
        default:
            throw new InvalidCommandFormatException(SortCommand.getFormat());
        }

        if (sortOrder.equals(SortOrder.DESCENDING)) {
            Collections.reverse(tasks);
        }
    }

    /**
     * Sets the current sort order of the TaskList to be the given sortOrder.
     *
     * @param sortOrder Sort order of the TaskList.
     */
    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Sets the current sort metric of the TaskList to be the given sortMetric.
     *
     * @param sortMetric Sort metric of the TaskList.
     */
    public void setSortMetric(SortMetric sortMetric) {
        this.sortMetric = sortMetric;
    }

    /**
     * Returns the number of tasks in the List of tasks.
     *
     * @return Size of the List of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Filters the list of Task objects in the TaskList, keep Task objects with descriptions matching the search term.
     * Returns the list of found Task objects as a new TaskList.
     *
     * @param searchTerm Search term used to find the list of matching Tasks.
     * @return TaskList containing the list of found Task objects.
     */
    public TaskList find(String searchTerm) {
        List<Task> foundTasksList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                foundTasksList.add(task);
            }
        }
        return new TaskList(foundTasksList);
    }

    /**
     * Returns the List of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the string representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int len = tasks.size();
        if (len == 0) {
            return "YOU HAVE NO TASKS :<\n";
        }

        for (int i = 0; i < len; i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d.%s", i + 1, task));
            if (i != len - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks if index is a valid index of a Task object in the List of tasks.
     *
     * @param index Index of a Task object.
     * @throws TaskIndexOutOfBoundsException If index of a Task object is invalid.
     */
    private void checkIsValidIndex(int index) throws TaskIndexOutOfBoundsException {
        boolean isValid = index >= 1 && index <= getSize();
        if (!isValid) {
            throw new TaskIndexOutOfBoundsException(index, getSize());
        }
    }
}
