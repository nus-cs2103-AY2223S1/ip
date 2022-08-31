package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * The TaskList class contains the List of Tasks and has operations to add
 * or delete tasks in the List.
 *
 * @author Edric Yeo
 */
public class TaskList {

    /** List containing all the tasks currently in the TaskList */
    private List<Task> tasks;

    /**
     * Constructor for a TaskList instance containing a list of tasks.
     *
     * @param tasks The List of Tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for a TaskList instance that has no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method that returns the number of Tasks in the List.
     *
     * @return The number of Tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Method that returns a Task at a given index of the List.
     *
     * @param idx The index of the task.
     * @return The Task at the given index.
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Method that adds a Task to the List.
     *
     * @param task The Task to add to the List.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Method that deletes a Task at a given index of the List.
     *
     * @param idx The index of the Task.
     * @return The deleted Task.
     */
    public Task deleteTask(int idx) {
        Task deleted = tasks.get(idx);
        tasks.remove(idx);
        return deleted;
    }

    /**
     * Method that marks a Task (as done) at a given index of the List.
     *
     * @param idx The index of the Task.
     * @return The Task marked as done.
     */
    public Task markTask(int idx) {
        Task task = tasks.get(idx);
        task.mark();
        return task;
    }

    /**
     * Method that marks a Task (as not done) at a given index of the List.
     *
     * @param idx The index of the Task.
     * @return The Task marked as not done.
     */
    public Task unmarkTask(int idx) {
        Task task = tasks.get(idx);
        task.unmark();
        return task;
    }

    /**
     * Method that saves and updates the data file.
     *
     * @param filePath The filePath to the data file to write to.
     */
    public void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            // System.out.println(curr.toDataEntry());
            fw.write(curr.toDataEntry());
        }
        fw.close();
    }

    /**
     * Method that finds tasks that are similar to a given keyword.
     *
     * @param keyword The String representing the matching keyword.
     * @return The TaskList containing the matching words.
     */
    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            String desc = curr.toString();
            if (desc.contains(keyword)) {
                matchingTasks.addTask(curr);
            }
        }
        return matchingTasks;
    }
}
