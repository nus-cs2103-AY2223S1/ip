package duke.task;

import static java.util.stream.Collectors.toCollection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import duke.exception.DukeException;

/**
 * Represents a list of Tasks.
 */
public class TaskList implements Iterable<Task> {
    public static final String ERROR_NO_TASKS_TO_OPERATE_ON = "You don't have tasks.";
    public static final String ERROR_INDEX_GIVEN_TOO_SMALL = "Task number should be at least 1.";
    public static final String ERROR_FEWER_TASKS_THAN_INDEX = "You only have %d tasks.";

    private ArrayList<Task> taskArrayList;

    /**
     * Initialises an empty task list.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Initialises a task list with existing tasks.
     *
     * @param taskArrayList List of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Returns number of tasks in the task list.
     *
     * @return Number of tasks.
     */
    public int taskCount() {
        return this.taskArrayList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Returns a specified task.
     *
     * @param index Index of task (1 - indexed).
     * @return Task with that index.
     * @throws DukeException if index is more than the number of tasks or index < 1.
     */
    public Task getTask(int index) throws DukeException {
        int numTasks = this.taskArrayList.size();

        if (numTasks == 0) {
            throw new DukeException(TaskList.ERROR_NO_TASKS_TO_OPERATE_ON);
        }
        if (index < 1) {
            throw new DukeException(TaskList.ERROR_INDEX_GIVEN_TOO_SMALL);
        }
        if (index > numTasks) {
            throw new DukeException(String.format(TaskList.ERROR_FEWER_TASKS_THAN_INDEX, numTasks));
        }

        // The user gives 1-indexed numbers.
        return this.taskArrayList.get(index - 1);
    }

    /**
     * Marks task as done and returns it.
     *
     * @param index Index of task (1 - indexed).
     * @return Task marked as done.
     * @throws DukeException if index is more than the number of tasks or index < 1.
     */
    public Task markTask(int index) throws DukeException {
        Task task = getTask(index);
        task.markDone();
        return task;
    }

    /**
     * Marks task as not done and returns it.
     *
     * @param index Index of task (1 - indexed).
     * @return Task unmarked as done.
     * @throws DukeException if index is more than the number of tasks or index < 1.
     */
    public Task unmarkTask(int index) throws DukeException {
        Task task = getTask(index);
        task.unmarkDone();
        return task;
    }

    /**
     * Deletes task and returns deleted task.
     *
     * @param index Index of task (1 - indexed).
     * @return Deleted task.
     * @throws DukeException if index is more than the number of tasks or index < 1.
     */
    public Task deleteTask(int index) throws DukeException {
        Task task = getTask(index);
        this.taskArrayList.remove(index - 1);
        return task;
    }

    /**
     * Returns tasks on that date.
     *
     * @param date
     * @return ArrayList of tasks on specified date.
     */
    public ArrayList<Task> getTasksOn(LocalDate date) {
        return this.filterTasks(task -> task.isOn(date));
    }

    /**
     * Returns a filtered task list where description contains the search query.
     * @param searchQuery String to search for.
     * @return ArrayList of type Task matching the search description.
     */
    public ArrayList<Task> searchTasks(String searchQuery) {
        return this.filterTasks(task -> task.descriptionIncludes(searchQuery));
    }

    private ArrayList<Task> filterTasks(Predicate<? super Task> predicate) {
        ArrayList<Task> filteredTaskList = this.taskArrayList.stream()
                .filter(predicate)
                .collect(toCollection(ArrayList::new));
        return filteredTaskList;
    }

    /**
     * Returns iterator so TaskList is iterable.
     * @return Iterator to iterate tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.taskArrayList.iterator();
    }

}
