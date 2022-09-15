package alpha;

import java.util.ArrayList;
import java.util.List;

import alpha.task.Task;

/**
 * Operates on the list of tasks.
 */
public class TaskList {

    /** List of tasks to be operated on */
    protected List<Task> tasks;

    /**
     * Constructor to initialise all the global variables.
     *
     * @param tasks To initialise the list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to the task list.
     *
     * @param task Task to be added to the list.
     */
    public void addToTaskList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the list of tasks in the task list.
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Modifies the status of the task at the provided index in the list.
     *
     * @param taskNumber The task number of the task whose status needs to be modified.
     * @param taskStatus The status to which the task status needs to be modified.
     * @throws AlphaException If the task number is out of bounds of the task list.
     */
    public Task modifyTaskStatus(int taskNumber, boolean taskStatus) throws AlphaException {
        Task task;
        try {
            task = tasks.get(taskNumber - 1);
            tasks.get(taskNumber - 1).changeStatus(taskStatus);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
        return task;
    }

    /**
     * Deletes task of the provided task number from the task list.
     *
     * @param taskNumber The task number of the task that needs to be deleted.
     * @throws AlphaException If the task number is out of bounds.
     */
    public Task deleteTask(int taskNumber) throws AlphaException {
        Task task;
        try {
            task = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
        return task;
    }

    /**
     * Filters the list of tasks and returns all tasks that contains the given keyword.
     *
     * @param keyword Keyword used to filter the list of tasks.
     * @return List of all tasks containing the keyword.
     */
    public List<Task> filterTaskDescription(String keyword) {
        List<Task> filteredTaskList = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Filters the list of tasks and returns all tasks that is tagged with the input tag.
     *
     * @param tag Tag used to filter the list of tasks.
     * @return List of all tasks containing the keyword.
     */
    public List<Task> filterTaskTag(String tag) {
        List<Task> filteredTaskList = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getTag().contains(tag)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Adds a tag to a task with the input task number in the task list.
     * @param taskNumber Task number of the task to be tagged.
     * @param tag Tag to be added to the task.
     * @throws AlphaException
     */
    public Task addTagToTask(int taskNumber, String tag) throws AlphaException {
        Task task;
        try {
            task = tasks.get(taskNumber - 1);
            tasks.get(taskNumber - 1).addTag(tag);
        } catch (IndexOutOfBoundsException e) {
            throw new AlphaException("Invalid input: This task number doesn't exist!");
        }
        return task;
    }
}
