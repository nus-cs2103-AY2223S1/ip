package duke;

import java.util.ArrayList;

import duke.task.Task;

/** Represents a list of Tasks objects */
public class TaskList {
    private final ArrayList<Task> taskList;
    private Ui ui;

    /**
     * Represents a TaskList object.
     * Initialises a new empty ArrayList of Tasks and Initialises ui to print responses when task methods are executed
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Represents a TaskList object.
     * Initialises taskList ArrayList with the provided taskList and Initialises ui to print responses
     * when task methods are executed
     *
     * @param taskList An existing taskList ArrayList to initialise TaskList with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns the size of the TaskList.
     * The returned integer represents the number of Task objects in TaskList.
     *
     * @return Size of the TaskList.
     */
    public int getTaskListSize() {
        return this.taskList.size();
    }

    /**
     * Returns the Task object stored at the particular index of the TaskList.
     *
     * @param index Index of the Task object.
     * @return Task object that is stored at given index.
     * @throws DukeException If provided index is out of range.
     */
    public Task getTaskAtIndex(int index) throws DukeException {
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        return this.taskList.get(index - 1);
    }

    /**
     * Adds the Task object into the TaskList at the end.
     *
     * @param task Task object to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the Task object from the TaskList at specified index.
     *
     * @param index Index of the Task object to be deleted.
     * @throws DukeException If provided index is out of range.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        index = index - 1;
        Task task = this.taskList.remove(index);
        return task;
    }

    /**
     * Updates the status of the Task object at specified index
     *
     * @param index Index of the Task object which needs its task status modified.
     * @param isDone True to mark task as done and false to mark task as not done
     * @throws DukeException If provided index is out of range.
     */
    public Task updateTaskStatus(int index, boolean isDone) throws DukeException {
        if (index <= 0 || index > getTaskListSize()) {
            throw new DukeException("☹ OOPS!!! The task index is out of range");
        }
        index = index - 1;
        Task task = this.taskList.get(index);
        task.setTaskStatus(isDone);
        this.taskList.set(index, task);
        return task;
    }

    /**
     * Returns the string representation of the TaskList showing all the Tasks inside to be printed to user.
     *
     * @return String representation of the TaskList showing all the Tasks inside to be printed to user..
     */
    public String printTaskList() {
        String message = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            message += (i + 1) + "." + task.toString() + "\n";
        }
        return message;
    }


}
