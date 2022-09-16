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
     * Initializes an empty {@code TaskList}
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a {@code TaskList} that contains existing tasks that have been saved
     * @param taskList List of existing tasks to be loaded
     */
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds Task to the current TaskList
     * @param t the task object to add to the TaskList
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes task from the current TaskList
     * @param index index of the task to be removed from the {@code TaskList}
     */
    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Gets task from the TaskList based on index
     * @param index index of the task to be retrieved from the {@code TaskList}
     * @return the selected task
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Loops through the TaskList and create a string representing all the tasks
     * @return returns a string of all tasks in the TaskList
     */
    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.size())
                .forEach(index -> sb.append(index + 1).append(". ").append(tasks.get(index)).append("\n"));

        return sb.toString();
    }

    /**
     * Searches the list for a task with matching description
     * @param query string to search for in the TaskList
     * @return a list of tasks that match the query
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

}
