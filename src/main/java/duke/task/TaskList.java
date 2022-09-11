package duke.task;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.common.DukeException;

/**
 * The list of tasks saved by Duke.
 *
 * @author Rama Aryasuta Pangestu
 */
public class TaskList {
    private final ArrayList<ArrayList<Task>> previousTasks;
    private ArrayList<Task> tasks;

    /**
     * Constructs the list of tasks which is initially empty.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.previousTasks = new ArrayList<>();
    }

    /**
     * Constructs the list of tasks which is initially equal to <code>tasks</code>.
     *
     * @param tasks the list of initial tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.previousTasks = new ArrayList<>();
    }

    /**
     * Constructs a new version of the list of tasks.
     */
    private void createNewVersion() {
        ArrayList<Task> newVersion = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i += 1) {
            newVersion.add(tasks.get(i).clone());
        }
        previousTasks.add(newVersion);
    }

    /**
     * Reverts to the last version of the task list.
     */
    public void undo() throws DukeException {
        if (previousTasks.size() == 0) {
            throw new DukeException("There are no more commands to undo :(");
        }
        int index = previousTasks.size() - 1;
        tasks = new ArrayList<>(previousTasks.get(index));
        previousTasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks containing only the tasks in which the specified predicate returns true.
     *
     * @param predicate the specified predicate
     * @return a <code>TaskList</code> containing tasks in which the specified predicate returns true
     */
    public TaskList filter(Predicate<Task> predicate) {
        return new TaskList(tasks.stream().filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Adds a task into the list.
     *
     * @param task the task added to the list
     */
    public void addTask(Task task) {
        createNewVersion();
        tasks.add(task);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the index of the task marked as done
     * @return the task marked as done
     * @throws DukeException if the specified index is out of bounds
     */
    public Task markTask(int index) throws DukeException {
        createNewVersion();
        try {
            tasks.get(index).setDone(true);
            assert 0 <= index && index < tasks.size();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException exception) {
            undo();
            throw new DukeException("OOPS!!! No such task exists :(");
        }
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the index of the task marked as not done
     * @return the task marked as not done
     * @throws DukeException if the specified index is out of bounds
     */
    public Task unMarkTask(int index) throws DukeException {
        createNewVersion();
        try {
            tasks.get(index).setDone(false);
            assert 0 <= index && index < tasks.size();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException exception) {
            undo();
            throw new DukeException("OOPS!!! No such task exists :(");
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the deleted task
     * @return the deleted task
     * @throws DukeException if the specified index is out of bounds
     */
    public Task deleteTask(int index) throws DukeException {
        createNewVersion();
        try {
            Task task = tasks.get(index);
            assert 0 <= index && index < tasks.size();
            tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException exception) {
            undo();
            throw new DukeException("OOPS!!! No such task exists :(");
        }
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i += 1) {
            stringBuilder.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return stringBuilder.toString();
    }
}
