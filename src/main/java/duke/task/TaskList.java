package duke.task;

import static duke.Storage.listToFile;

import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * A TaskList object containing the tasklist.
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     * @param arr arraylist of tasks.
     */
    public TaskList(ArrayList<Task> arr) {
        this.taskList = arr;
    }

    /**
     * Gets the task at that index.
     * @param index int of object.
     * @return Task at that index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the tasklist.
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
        listToFile(this.taskList);
    }

    /**
     * Removes the task at a specified index.
     * @param index of task to be removed.
     */
    public void remove(int index) {
        this.taskList.remove(index);
        listToFile(this.taskList);
    }

    /**
     * Returns the length of tasklist.
     * @return int length of tasklist.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks task as done.
     * @param index of task to be marked done.
     */
    public void mark(int index) {
        this.taskList.get(index).mark();
        listToFile(this.taskList);
    }

    /**
     * Marks task as not done.
     * @param index of task to be marked not done.
     */
    public void unMark(int index) {
        this.taskList.get(index).unMark();
        listToFile(this.taskList);
    }

    /**
     * Clears whole tasklist.
     */
    public void clear() {
        this.taskList.clear();
        listToFile(this.taskList);
    }

    /**
     * Finds the matching phrase.
     * @param phrase to be found.
     * @return List with matching phrases.
     */
    public ArrayList<Task> findMatching(String phrase) {
        return taskList
                .stream()
                .filter(task -> task.taskName.contains(phrase))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
