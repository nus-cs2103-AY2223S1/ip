package duke.util;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.exception.TaskAlreadyExistException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;


/**
 * Represents the memory that Duke uses for Tasks.
 */
public class TaskList implements Savable<TaskList> {
    /**
     * The collection of Tasks.
     */
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a given Task to the collection.
     * @param task The given task.
     */
    public void add(Task task) {
        boolean hasSameTask = tasks.stream().anyMatch(x -> x.isSameTask(task));
        if (hasSameTask) {
            throw new TaskAlreadyExistException(task);
        }
        tasks.add(task);
    }

    /**
     * Returns an identical TaskList as this.
     * @return An identical TaskList.
     */
    public TaskList clone() {
        TaskList result = new TaskList();
        tasks.forEach(task -> result.add(task.clone()));
        return result;
    }

    /**
     * Returns a boolean whether the given ArrayList is equals to the internal TaskList.
     * @param taskArrayList The given ArrayList of Tasks.
     * @return The boolean whether the given ArrayList is equals to the internal TaskList.
     */
    protected boolean isIdenticalTaskList(ArrayList<Task> taskArrayList) {
        return this.tasks == taskArrayList
                ? true
                : this.tasks == null || taskArrayList == null
                ? false
                : this.tasks.equals(taskArrayList);
    }

    /**
     * Returns the Task at given index.
     * @param i The given index.
     * @return The Task at given index.
     * @throws TaskNotFoundException if given index is invalid.
     */
    public Task get(int i) throws TaskNotFoundException {
        throwIfNotValidIndex(i);
        return this.tasks.get(i);
    }

    /**
     * Returns the size of the TaskList.
     * @return The Task size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns and removes the Task at given index.
     * @param i The given index.
     * @return The removed task.
     * @throws TaskNotFoundException if given index is invalid.
     */
    public Task remove(int i) throws TaskNotFoundException {
        throwIfNotValidIndex(i);
        return this.tasks.remove(i);
    }

    /**
     * Returns if the given index is valid.
     * @param i The given index.
     * @return The boolean if the given index is valid.
     */
    private boolean isValidIndex(int i) {
        return i >= 0 && i < this.size();
    }

    /**
     * Throws the exception if the given index is invalid.
     * @param i The given index.
     * @throws TaskNotFoundException if the given index is invalid.
     */
    private void throwIfNotValidIndex(int i) throws TaskNotFoundException {
        if (!isValidIndex(i)) {
            throw new TaskNotFoundException(i + 1);
        }
    }

    /**
     * Returns the String representation of the TaskList.
     * @return The String representation of the TaskList.
     */
    @Override
    public String toString() {
        String message = Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> x + 1 + ". " + tasks.get(x).toString())
                .reduce("", (x, y) -> x + y + "\n");
        return message;
    }

    /**
     * Returns the formatted string representation of the object.
     * @return The formatted string representation of the object.
     */
    @Override
    public String toFormattedString() {
        String s;
        s = Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> this.tasks.get(x).toFormattedString())
                .reduce("", (x, y) -> x + y + "\n");
        return s;
    }

    /**
     * Returns the TaskList represented by given formatted String.
     * @param formattedString The given formatted String.
     * @return The TaskList represented by given formatted String.
     */
    @Override
    public TaskList parse(String formattedString) {
        return Parser.parseTaskList(formattedString);
    }

    /**
     * Returns boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TaskList) {
            TaskList tl = (TaskList) obj;
            if (this.tasks == tl.tasks) {
                return true;
            }
            return tl.isIdenticalTaskList(this.tasks);
        }
        return false;
    }
}
