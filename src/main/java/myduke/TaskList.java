package myduke;

import exception.DukeException;
import exception.MarkException;
import exception.UnMarkException;
import exception.OutOfBoundIndexException;

import task.Task;

import java.util.ArrayList;

/**
 * This class contains a list of tasks and manages it.
 */
public class TaskList {
    //this is where the tasks are stored
    private final ArrayList<Task> taskLists;

    /**
     * Constructor for tasklist.
     */
    public TaskList() {
        this.taskLists = new ArrayList<>();
    }

    private boolean checkValidIndex(int index) {
        return index > -1 && index < taskLists.size();
    }

    /**
     * Returns the task with the given index.
     *
     * @param index index of the task you want to retrieve
     * @return the desired task.
     */
    public Task getTask(int index) {
        return taskLists.get(index);
    }

    /**
     * Returns the number of tasks stored.
     *
     * @return number of tasks stored.
     */
    public int getNumOfTask() {
        return taskLists.size();
    }

    /**
     * Saves the given task.
     *
     * @param task given task
     */
    public void saveTask(Task task) {
        taskLists.add(task);
    }

    /**
     * Marks the task as completed.
     *
     * @param index given index of the task
     * @throws DukeException
     */
    public void markTask(int index) throws MarkException, OutOfBoundIndexException {
        if (checkValidIndex(index)) {
            Task current = taskLists.get(index);
            if (current.getStatus()) {
                throw new MarkException();
            }
            current.markasDone();
        } else {
            throw new OutOfBoundIndexException();
        }
    }

    /**
     * Marks the task as incomplete.
     *
     * @param index given index of the task
     * @throws DukeException
     */
    public void unMarkTask(int index) throws UnMarkException, OutOfBoundIndexException {
        if (checkValidIndex(index)) {
            Task current = taskLists.get(index);
            if (!current.getStatus()) {
                throw new UnMarkException();
            }
            current.markasNotDone();
        } else {
            throw new OutOfBoundIndexException();
        }
    }

    /**
     * Deletes the tasks with the given index and returns it
     *
     * @param index given index
     * @return the deleted task
     * @throws DukeException
     */
    public Task deleteTask(int index) throws OutOfBoundIndexException {
        if (checkValidIndex(index)) {
            Task task = taskLists.get(index);
            taskLists.remove(index);
            return task;
        } else {
            throw new OutOfBoundIndexException();
        }
    }

    /**
     * Finds all tasks stored containing keyword and saves them in new TaskLists to be returned.
     *
     * @param keyword given keyword to find with.
     * @return a TaskList containing all tasks with the keyword.
     */
    public TaskList findTask(String keyword) {
        //taskList to be returned
        TaskList filteredTaskList = new TaskList();

        //filter saved Tasks with keyword
        for (int i = 0; i < taskLists.size(); i++) {
            Task current = taskLists.get(i);
            if (current.toString().contains(keyword)) {
                //if current task contains keyword, add it to filter TaskList
                filteredTaskList.saveTask(current);
            }
        }
        return filteredTaskList;
    }

    /**
     * Returns the string representation of the stored tasks.
     *
     * @return a table listing all the stored tasks
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < taskLists.size(); i++) {
            Task current = taskLists.get(i);
            output += (i + 1) + "." + current.toString();
            if (i != taskLists.size() - 1) {
                output = output + "\n";
            }
        }
        return output;
    }

    /**
     * Returns a string detailing how many tasks are stored.
     *
     * @return a string telling user how many tasks are in the list.
     */
    public String numOfTaskToString() {
        return "\n" + "Now you have " + taskLists.size() + " tasks in the list.";
    }
}
