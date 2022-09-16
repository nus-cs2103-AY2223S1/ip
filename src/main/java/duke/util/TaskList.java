package duke.util;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;

/**
 * A class to represent a list of tasks.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Displays all stored tasks.
     * 
     * @return String representation of all tasks in the task list.
     */
    public String displayList() {
        String output = "Here are the tasks in your list.\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += "\t" + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Adds task to tasks list.
     * 
     * @param task Task to be stored in task list.
     * @return String representation of task added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        assert task != null;
        String output = "Gotcha! I've added this task:\n";
        output += "\t" + task;
        output += "\nNow you have " + tasks.size() + " tasks in your list.";
        return output;
    }

    /**
     * Adds task to task list without any user messages (Used when loading data from file).
     * 
     * @param task Task to be pushed into task list.
     */
    public void pushTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes specified task from tasks list.
     * 
     * @param taskIndex Index of task to be removed.
     * @return String representation of task removed.
     * @throws DukeException if given index is out of bounds.
     */
    public String removeTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        }
        Task task = tasks.remove(taskIndex);
        assert task != null;
        String output = "Noted. I've removed this task:\n";
        output += "\t" + task;
        return output;
    }

    /**
     * Changes the completed status of specified task.
     * 
     * @param taskIndex Index of task to be changed.
     * @param isDone true if task is completed, false otherwise.
     * @return String representation of task whose status has been changed.
     * @throws DukeException if given index is out of bounds.
     */
    public String changeTaskStatus(int taskIndex, boolean isDone) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        }
        if (isDone) {
            return tasks.get(taskIndex).markAsDone();
        } else {
            return tasks.get(taskIndex).markAsNotDone();
        }
    }

    /**
     * Returns the string representation of all tasks in list to be stored in local file.
     * 
     * @return String representation of all tasks in list.
     */
    public String getFileText() {
        String fileString = "";
        for (Task task : tasks) {
            fileString += task.getFileString() + "\n";
        }
        return fileString;
    }

    /**
     * Finds the tasks matching the search text and displays them.
     * 
     * @param searchText Search text to be searched for
     * @return String representation of all tasks matching the search text.
     */
    public String findTask(String searchText) {
        int i = 0;
        String res = " Here are the matching tasks in your list: \n";
        for (Task task : tasks) {
            if (task.containsSearchText(searchText)) {
                res += "\t" + (++i) + ". " + task.toString() + "\n";
            }
        }
        if (i == 0) {
            res = "Sorry, no matching tasks were found!";
        }
        return res;
    }
}
