package task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a list for Task. A <code>TaskList</code> object corresponds to
 * an ArrayList of task.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Constructor for the <Code>TaskList</Code>.
     *
     * @param tasks A list of task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        size = tasks.size();
    }

    /**
     * Adds task to <Code>TaskList</Code>
     *
     * @param element Task to be inputted.
     */
    public void addTask(Task element) {
        tasks.add(element);
        size++;
    }

    /**
     * Adds task by index to <Code>TaskList</Code>.
     *
     * @param idx     Index to be slotted in.
     * @param element Task to be inputted.
     */
    public void addTaskByIdx(int idx, Task element) {
        tasks.add(idx, element);
        size++;
    }

    /**
     * Removes task from <Code>TaskList</Code>.
     *
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        tasks.remove((Task) task);
        size--;
    }

    /**
     * Gets Task from TaskList.
     *
     * @param idx Index that the Task is at.
     * @return Task at that index.
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Returns an ArrayList of task with the search word provided.
     *
     * @param searchWord The word that you want to search in the list.
     * @return An ArrayList of task with the search word provided.
     */
    public ArrayList<Task> findTasks(String searchWord) {
        ArrayList<Task> taskArr = new ArrayList<>();
        for (Task task : tasks) {
            if ((task.getName().toLowerCase()).contains(searchWord.toLowerCase())) {
                taskArr.add(task);
            }
        }
        return taskArr;
    }


    /**
     * Gets an ArrayList from TaskList.
     *
     * @return An ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Gets size from TaskList.
     *
     * @return How many Task is in a TaskList.
     */
    public int getSize() {
        return size;
    }

    /**
     * Converts TaskList to a string.
     *
     * @return A string that represent a TaskList.
     */
    @Override
    public String toString() {
        return Arrays.toString(tasks.toArray());
    }
}
