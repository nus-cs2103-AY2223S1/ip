package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.models.Task;


/**
 * Contains the task list and has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initialize an empty <code>TaskList</code>
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialize a <code>TaskList</code> that contains existing tasks that have been saved
     * @param taskList List of existing tasks to be loaded
     */
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Add Task to the current TaskList
     * @param t the task object to add to the TaskList
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Delete task from the current TaskList
     * @param index
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Get task from the TaskList based on index
     * @param index
     * @return the selected task
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Loop through the TaskList and create a string representing all the tasks
     * @return returns a string of all tasks in the TaskList
     */
    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.size())
                .forEach(index -> sb.append(index + 1).append(". ").append(tasks.get(index)).append("\n"));
        sb.append("Here are the notes in your list:\n");

        return sb.toString();
    }

    /**
     * Search the list for a task with matching description
     * @param query string to search for in the TaskList
     * @return
     */
    public List<Task> findTask(String query) {
        List<Task> resultList = tasks
                                    .stream()
                                    .filter(e -> e.getDescription().contains(query))
                                    .collect(Collectors.toList());
        return resultList;
    }

    /**
     * Return the size of the current TaskList
     * @return the size of the TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Postpone the {@code Task} at the given index by one day
     * @param index
     */
    public void postponeTask(int index) {
        Task task = tasks.get(index - 1);
        task.postponeTask();
    }
}
