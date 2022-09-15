package duke.tasklist;

import duke.tasks.Task;

import java.util.List;
import java.util.ArrayList;


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
    public String markTaskComplete(int index) {
        taskList.get(index).markAsDone();
        String res = "Nice! I've marked this task as having been completed:\n";
        res += taskList.get(index).toString();

        assert (taskList.get(index).getStatusIcon().equals("X")) : "Task should be properly marked as complete";
        return res;
    }

    /**
     * Marks a task as incomplete in the Task List.
     *
     * @param index The index of the task to be marked as incomplete.
     */
    public String markTaskIncomplete(int index) {
        taskList.get(index).markAsUndone();
        String res = "Okay, I've marked this task as not done yet:\n";
        res += taskList.get(index).toString();

        assert (taskList.get(index).getStatusIcon().equals(" ")) : "Task should be properly marked as incomplete";
        return res;
    }

    /**
     * Adds a task to the Task List.
     *
     * @param task The task that is to be added to the List.
     */
    public String addTaskToList(Task task) {
        taskList.add(task);
        String res = "Got it, i've added this task to your list:\n  " + task + "\n";
        res += "You now have " + taskList.size() + " tasks in your list.";

        assert (taskList.contains(task)) : "Task should be properly added to the list";
        return res;
    }

    /**
     * Deletes a task from the Task List.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        String res = "Okay, I've removed this task:\n";
        res += removedTask.toString();
        res += "\nYou now have " + taskList.size() + " tasks in your list.";

        assert (!taskList.contains(removedTask)) : "Task should be properly deleted from the list";
        return res;
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
        StringBuilder result = new StringBuilder("");
        for (Task task: this.taskList) {
            result.append(task.getSaveString() + "\n");
        }
        return result.toString();
    }

    /**
     * Method to get reminders for tasks that are due in 3 days.
     * @return
     */
    public String getReminders() {
        String extra = "Hey, these are some of your tasks that are due in the next 3 days:\n";
        StringBuilder result = new StringBuilder("");
        for (Task task: this.taskList) {
            if (task.isWithinDeadline() && task.getStatusIcon().equals(" ")) {
                result.append(task.toString() + "\n");
            }
        }
        if (result.toString().equals("")) {
            return "You have no upcoming tasks!";
        }
        return extra + result.toString().substring(0, result.length() - 1);
    }

    @Override
    public String toString() {
        int counter = 1;
        StringBuilder res = new StringBuilder("Here are the tasks that you have added to the list:\n");
        for (Task task : this.taskList) {
            if (task != null) {
                res.append(counter + ". " + task);
                if (counter != taskList.size()) {
                    res.append("\n");
                }
                counter++;
            }
        }
        return counter == 1 ? "There are no tasks in your task list at the moment!" : res.toString();
    }
}
