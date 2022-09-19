package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the duke.Duke application.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    /**
     * Constructor for duke.TaskList which initialises an ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add a new task to the task list
     *
     * @param task to be added to the task list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove the task in the given numerical position in the task list, starting from 1
     * @param index of the task to be removed
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieve the task in the given numerical position in the task list, starting from 1
     * @param index of the task to be retrieved
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Size gives the size of the task list
     *
     * @return the total number of tasks in the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Find the task in the task list with matching keyword
     *
     * @param keyword to be found in the description of the task
     * @return a string of tasks which have the matching keyword
     */
    public String find(String keyword) {
        String message = "Here are the matching tasks in your list!\n%s";

        ArrayList<String> foundTasks = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            Task task = this.get(i);
            if (task.toString().contains(keyword)) {
                String taskString = String.format("%d: %s", i + 1, task);
                foundTasks.add(taskString);
            }
        }
        String tasksString = String.join("\n", foundTasks);
        return String.format(message, tasksString);
    }
}
