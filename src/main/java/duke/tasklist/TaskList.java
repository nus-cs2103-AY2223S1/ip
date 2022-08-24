package duke.tasklist;

import duke.tasks.Task;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Implementation of the TaskList, the list of tasks created by the user.
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor to allow for loading of previously generated tasks.
     *
     * @param taskList Array of existing tasks parsed by Storage
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks a task as complete in the Task List.
     *
     * @param index The index of the task to be marked as complete
     */
    public void markTaskComplete(int index) {
        taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as having been completed:");
        System.out.println(taskList.get(index));
    }

    /**
     * Marks a task as incomplete in the Task List.
     *
     * @param index The index of the task to be marked as incomplete.
     */
    public void markTaskIncomplete(int index) {
        taskList.get(index).markAsUndone();
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(taskList.get(index));
    }

    /**
     * Adds a task to the Task List.
     *
     * @param task The task that is to be added to the List.
     */
    public void addTaskToList(Task task) {
        taskList.add(task);
        System.out.println("Got it, i've added this task to your list:\n  " + task);
        System.out.println("You now have " + taskList.size() + " tasks in your list.");
    }

    /**
     * Deletes a task from the Task List.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("Okay, I've removed this task:");
        System.out.println(removedTask);
        System.out.println("You now have " + taskList.size() + " tasks in your list.");
    }

    /**
     * Searches for tasks in the list that contain any of the search terms.
     * Search terms are all individual words that come after the [list] keyword.
     * For an example, "list new test" will return all tasks in the list that have
     * "new" or "test" in their descriptions.
     *
     * The current implementation of search only supports exact matching (case-insensitive).
     *
     * @param terms Array of search terms.
     * @return String that contains all matching tasks, formatted.
     */
    public String search(String[] terms) {
        String result = "";
        for (Task task: this.taskList) {
            for (String term: terms) {
                if (task.toString().toLowerCase().contains(term.toLowerCase())) {
                   if (!result.contains(task.toString())) {
                       result += task.toString() + "\n";
                   }
                }
            }
        }
        return result.equals("") ? result : result.substring(0, result.length() - 1);
    }

    /**
     * Fetch the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns write string version of TaskList.
     *
     * @return TaskList in writeString format, to be written into data.txt
     */
    public String getWriteString() {
        String result = "";
        for (Task task: this.taskList) {
            result += task.getSaveString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        int counter = 1;
        String res = "Here are the tasks that you have added to the list:\n";
        for (Task task : this.taskList) {
            if (task != null) {
                res += counter + ". " + task;
                if (counter != taskList.size()) {
                    res += "\n";
                }
                counter++;
            }
        }
        return counter == 1 ? "There are no tasks in your task list at the moment!" : res;
    }
}
