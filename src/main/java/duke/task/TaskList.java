package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * This class encapsulates a list of Task-s along with its associated operations.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided array of Tasks.
     */
    public TaskList(Task... taskList) {
        this.taskList = new ArrayList<>(Arrays.asList(taskList));
    }

    /**
     * Adds the specified Task onto this TaskList.
     *
     * @param task the specified Task parameter.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the Task at the specified index from this TaskList.
     *
     * @param index the specified index parameter.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Clears this TaskList.
     */
    public void clear() {
        taskList.clear();
    }

    /**
     * Returns an array of String, containing the String representation
     * of each Task in this TaskList.
     *
     * @return a String[].
     */
    public String[] toStringList() {
        return taskList.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

    /**
     * Returns the length of the list.
     *
     * @return length of the list.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns the Task at the specified index in this TaskList.
     *
     * @param index the specified index parameter.
     * @return a Task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns a TaskList consisting only of Tasks that satisfy the given predicate.
     *
     * @param pred a predicate to apply to each Task to determine if it should be included
     * @return the new TaskList
     */
    public TaskList filter(Predicate<? super Task> pred) {
        return new TaskList(taskList.stream().filter(pred).toArray(Task[]::new));
    }
}
