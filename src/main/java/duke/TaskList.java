package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidInput;
import duke.task.Task;


/**
 * A class that encapsulates the storing of all the task data.
 */
public class TaskList {
    /** The ArrayList that contains all the tasks */
    private final ArrayList<Task> data;

    /**
     * The class constructor that initialises the ArrayList.
     */
    public TaskList() {
        this.data = new ArrayList<>();
    }

    /**
     * Returns the number of tasks saved.
     *
     * @return The size of the ArrayList.
     */
    public int size() {
        return data.size();
    }

    /**
     * Returns the tasks saved in the provided index.
     *
     * @param index The index of the tasks of interest.
     * @return The task of interest.
     */
    public Task get(int index) {
        return data.get(index);
    }

    /**
     * Appends a new task to the ArrayList.
     *
     * @param task The new task to append.
     */
    public void add(Task task) {
        data.add(task);
    }

    /**
     * Checks if the number provided is valid and returns the number in int.
     *
     * @param res The string to check.
     * @return The valid index in Integer.
     * @throws InvalidInput If the string given is not a number or the number is
     *                      less than 0 or more than the size of the ArrayList.
     */
    public int stringIndexToInt(String res) throws InvalidInput {
        if (!res.matches("[0-9]+")) {
            throw new InvalidInput("Input is not a number");
        }
        int targetIndex = Integer.parseInt(res) - 1;
        if (targetIndex < 0 || targetIndex >= data.size()) {
            throw new InvalidInput("Please input a correct number");
        }
        return targetIndex;
    }

    /**
     * Marks the task as complete or not complete.
     *
     * @param indexString The string containing the index of the task.
     * @param isComplete Determines whether to mark as complete or incomplete.
     * @return The marked or unmarked task.
     * @throws DukeException If the provided string is not valid.
     */
    public Task setTaskCompletion(String indexString, boolean isComplete) throws DukeException {
        Task task = data.get(stringIndexToInt(indexString));
        if (isComplete) {
            task.markDone();
        } else {
            task.markNotDone();
        }
        return task;
    }

    /**
     * Deletes a specified task.
     *
     * @param indexString The string containing the index of the task.
     * @return The deleted task.
     * @throws DukeException If the provided string is not valid.
     */
    public Task deleteTask(String indexString) throws DukeException {
        int targetIndex = stringIndexToInt(indexString);
        Task task = data.get(targetIndex);
        data.remove(targetIndex);
        return task;
    }

    /**
     * Returns an ArrayList of tasks that matches the given query.
     *
     * @param query The query inputted by the user.
     * @return An ArrayList of tasks matching the query.
     */
    public TaskList find(String query) {
        TaskList res = new TaskList();
        for (Task t : data) {
            String taskDescription = t.getDescription();
            if (taskDescription.contains(query)) {
                res.add(t);
            }
        }
        return res;
    }

    /**
     * Converts the TaskList object to its string representation.
     *
     * @return The string representing the TaskList.
     */
    @Override
    public String toString() {
        if (data.size() == 0) {
            return "Nothing here...";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            output.append(i + 1).append(". ").append(task).append("\n");
        }
        return output.substring(0, output.length() - 1);
    }

}
