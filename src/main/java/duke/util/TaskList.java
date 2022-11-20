package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The object Duke uses to keep track of the user's input tasks
 *
 * @author Nephelite
 * @version 0.1
 */
public class TaskList {
    /**
     * ArrayList of Tasks that Duke uses to track the user's input tasks
     */
    private ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList object, if there was a prior saved ArrayList
     *
     * @param taskList ArrayList of tasks to construct a TaskList from
     * @since 0.1
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Constructor for a TaskList object, if there was no prior saved ArrayList
     *
     * @since 0.1
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the size of tasks
     *
     * @return the size of taskList
     * @since 0.1
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task of an index from tasks
     *
     * @param taskId index of the desired task
     * @return the task at index in taskList
     * @since 0.1
     */
    public Task getTask(int taskId) {
        assert(taskId < tasks.size() && taskId >= 0);
        return tasks.get(taskId);
    }

    /**
     * Removes the task of an index from tasks
     *
     * @param taskId index of the desired task
     * @since 0.1
     */
    public void remove(int taskId) {
        assert(taskId < tasks.size() && taskId >= 0);
        tasks.remove(taskId);
    }

    /**
     * Finds all the tasks in tasks that possesses the input word
     *
     * @param word String to check
     * @return ArrayList of all matching tasks
     * @since 0.1
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isTaskAndWordMatch(word)) {
                matches.add(tasks.get(i));
            }
        }
        return matches;
    }

    /**
     * Adds a task to tasks
     *
     * @param task Task to add
     * @since 0.1
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Prints the string representations of all tasks in tasks,
     * with a 1-indexed numbering system.
     *
     * @since 0.1
     */
    public String printList() {
        if (tasks == null || tasks.size() <= 0) {
            return "\nNo tasks assigned yet.";
        } else {
            String response = "";
            for (int i = 0; i < tasks.size(); i++) {
                response += "\n" + (i + 1) + ". " + getTask(i);
                response += getTask(i).getTags();
            }
            return response;
        }
    }
}
