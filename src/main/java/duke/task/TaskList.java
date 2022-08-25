package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A TaskList class that encapsulates the information and actions that are performed by the task list.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a TaskList Object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList Object from another TaskList.
     * @param taskList the other TaskList
     */
    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Gets the current list of all the tasks.
     * @return
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the list.
     * @return the number of tasks.
     */
    public Integer getNumOfRemainingTasks() {
        return tasks.size();
    }

    /**
     * Marks a task in the task list as done.
     * @param index the position of the task in the list
     * @return the selected Task object
     */
    public Task markTask(Integer index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }
    /**
     * Marks a task in the task list as not done
     * @param index the position of the task in the list
     * @return the selected Task object
     */
    public Task unmarkTask(Integer index) {
        Task task = tasks.get(index);
        task.unMark();
        return task;
    }

    /**
     * Deletes a task in the task list
     * @param index the position of the task that needs to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Adds a task to the current task list
     * @param task the task that needs to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

}
