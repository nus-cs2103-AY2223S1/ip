package myduke;
import task.Task;

import java.util.ArrayList;

/**
 * This class contains a list of tasks and manages it.
 */
public class TaskList {
    //this is where the tasks are stored
    private ArrayList<Task> taskLists;

    /**
     * Constructor for tasklist.
     */
    public TaskList() {
        this.taskLists = new ArrayList<Task>();
    }

    /**
     * Returns the task with the given index.
     * @param index index of the task you want to retrieve
     * @return the stored task
     */
    public Task getTask(int index) {
        return taskLists.get(index);
    }

    /**
     * Returns the number of tasks stored
     * @return number of tasks stored
     */
    public int getNumOfTask() {
        return taskLists.size();
    }

    /**
     * Adds the given task into the taskList
     * @param task given task
     */
    public void add(Task task) {
        taskLists.add(task);
    }

    /**
     * Saves the given task.
     * @param task given task
     */
    public void saveTask(Task task) {
        taskLists.add(task);
    }

    /**
     * Marks the task as completed.
     * @param index given index of the task
     * @throws DukeException
     */
    public void markTask(int index) throws DukeException {
        if (checkValidIndex(index)) {
            Task current = taskLists.get(index);
            if (current.getStatus()) {
                throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
            }
            current.markasDone();
        } else {
            throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
        }
    }

    /**
     * Marks the task as incomplete.
     * @param index given index of the task
     * @throws DukeException
     */
    public void unMarkTask(int index) throws DukeException {
        if (index > -1 && index < taskLists.size()) {
            Task current = taskLists.get(index);
            if (!current.getStatus()) {
                throw new DukeException("☹ OOPS!!! The task you want to mark is already marked.");
            }
            current.markasNotDone();
        } else {
            throw new DukeException("☹ OOPS!!! The task you want to mark is not here.");
        }
    }

    /**
     * Deletes the tasks with the given index and returns it
     * @param index given index
     * @throws DukeException
     * @return the deleted task
     */
    public Task deleteTask(int index) throws DukeException {
        if (checkValidIndex(index)) {
            Task task = taskLists.get(index);
            taskLists.remove(index);
            return task;
        } else {
            throw new DukeException("☹ OOPS!!! The The task you want to delete is not here.");
        }
    }

    private boolean checkValidIndex(int index) {
        return index > -1 && index < taskLists.size();
    }

    /**
     * Returns the string representation of the stored tasks.
     * @return a table listing all the stored tasks
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < taskLists.size(); i ++) {
            Task current = taskLists.get(i);
            output = output + String.valueOf(i + 1) + "." + current.toString();
            if (i != taskLists.size() - 1) {
                output = output + "\n";
            }
        }
        return output;
    }

    /**
     * Returns a string detailing how many tasks are stored.
     * @return a string
     */
    public String numOfTaskToString() {
        return "\n" + "Now you have " + String.valueOf(taskLists.size()) + " tasks in the list.";
    }
}
