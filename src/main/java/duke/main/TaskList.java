package duke.main;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Class encapsulating task list and operations on task list.
 */
public class TaskList {
    /* List of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructor of TaskList class.
     *
     * @param tasks ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds Task into task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves Task from task list given index of task as a string.
     * Throws DukeException if given index is invalid.
     *
     * @param indexString String containing index of task to be retrieved.
     * @return Task corresponding to given index in task list.
     * @throws DukeException If given index is invalid.
     */
    public Task getTask(String indexString) throws DukeException {
        try {
            int index = Integer.parseInt(indexString); // Throw NFE if invalid int
            Task task = this.tasks.get(index - 1); // Throws IOOBE if invalid index
            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, that Task Number doesn't look right...");
        }
    }

    /**
     * Outputs all tasks in task list or error message if no tasks are found.
     */
    public String display() {
        if (this.tasks.size() == 0) {
            return "Looks like you don't have any tasks for now!";
        }
        return getTaskList();
    }

    /**
     * Return string representation of tasks in task list.
     *
     * @return String representation of tasks in task list.
     */
    private String getTaskList() {
        assert tasks.size() > 0 : "Empty task list";

        String taskListString = "";
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            taskListString += String.format("%d. %s\n", i, currTask);
        }
        return taskListString;
    }

    /**
     * Deletes task at given index from task list.
     *
     * @param indexString String input containing index of task to be deleted.
     */
    public void deleteTask(String indexString) throws DukeException {
        try {
            Task task = getTask(indexString);
            this.tasks.remove(task);

        } catch (DukeException de) {
            throw de;
        }
    }

    /**
     * Sets task status of task at given index of task list as completed or not completed.
     *
     * @param indexString String input containing index of task to be marked.
     * @param keyword     Keyword value to determine whether to mark or unmark the task.
     */
    public void markUnmarkTask(String indexString, Keyword keyword) throws DukeException {
        try {
            Task task = this.getTask(indexString);

            switch (keyword) {
            case MARK: {
                task.markAsDone();
                break;
            }
            case UNMARK: {
                task.markAsNotdone();
                break;
            }
            default: {
                throw new DukeException("Unexpected error in markUnmarkTask");
            }
            }
        } catch (DukeException de) {
            throw de;
        }

    }

    /**
     * Returns number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Outputs list of tasks that contain the searchTerm string.
     *
     * @param searchTerm Term to be searched for.
     */
    public String search(String searchTerm) {
        if (this.tasks.size() == 0) {
            return "Looks like you don't have any tasks for now!";
        }

        String output = searchTaskList(searchTerm);
        return validateSearchResults(output);
    }

    /**
     * Searches task list for tasks containing search term.
     *
     * @param searchTerm String to be searched.
     * @return String containing list of matching tasks.
     */
    private String searchTaskList(String searchTerm) {
        int count = 1;
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (isMatchingTask(currTask, searchTerm)) {
                output += String.format("%d. %s\n", count, currTask);
                count++;
            }
        }
        return output;
    }

    /**
     * Returns true if string representation of task contains search term.
     * Return false otherwise.
     *
     * @param task Task to be checked.
     * @param searchTerm String to check against.
     * @return True if task contains search term, false otherwise.
     */
    private boolean isMatchingTask(Task task, String searchTerm) {
        return task.toString().contains(searchTerm);
    }

    /**
     * Returns error message if output string is empty.
     * Return output string unchanged, otherwise.
     *
     * @param output String to be validated.
     * @return Error message if output string is empty.
     */
    private String validateSearchResults(String output) {
        if (output == "") {
            return "I don't think we have that one..";
        }
        return output;
    }

    /**
     * Assigns a tag with given tag name to task of given index in task list.
     *
     * @param indexString Index of task to be tagged.
     * @param tagName Name of tag.
     * @throws DukeException If task is not found.
     */
    public void tagTask(String indexString, String tagName) throws DukeException {
        try {
            Task task = this.getTask(indexString);
            task.tag(tagName);
        } catch (DukeException de) {
            throw de;
        }
    }

}
