package iana.tasks;

import iana.exception.IanaException;
import iana.tasks.TaskList;
import java.util.ArrayList;

/**
 * Represents the list that contains and manage all the current tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a task list given an ArrayList of Task.
     * @param taskList ArrayList of task that contains current tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get the number of tasks in the task list.
     * @return number of tasks.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Add a new task into the task list.
     * @param newTask new task to be added.
     * @throws IanaException if task is in the incorrect format.
     */
    public void add(Task newTask) throws IanaException {
        this.taskList.add(newTask);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNumber id of task to be deleted.
     * @return task that is deleted.
     * @throws IanaException if task number does not exist in current task list.
     */
    public Task delete(int taskNumber) throws IanaException {
        if (taskNumber > this.taskList.size()) {
            throw new IanaException("Oops!! This task number is invalid. Try to delete another task! xx");
        }
        Task deleted = this.taskList.remove(taskNumber);
        return deleted;
    }

    /**
     * Marks the task as completed.
     * @param taskNumber id of task to be marked.
     */
    public void mark(int taskNumber) {
        this.taskList.get(taskNumber).toggleComplete(true);
    }

    /**
     * Marks the task as incomplete.
     * @param taskNumber id of task to be unmarked.
     */
    public void unmark(int taskNumber) {
        this.taskList.get(taskNumber).toggleComplete(false);
    }

    /**
     * Find tasks with specified keyword.
     * @param keyword keyword of task.
     * @return a list of all tasks with the keyword.
     */
    public TaskList findKeyword(String keyword) {
        TaskList list = new TaskList();
        for (Task task : this.taskList) {
            if (task.containsKeyword(keyword)) {
                try {
                    list.add(task);
                } catch (IanaException e) {
                    break;
                }
            }
        }
        return list;
    }

    /**
     * Return string representation of task.
     * @param taskNumber id of task to be printed.
     * @return string representation of task.
     */
    public String printTaskString(int taskNumber) {
        return this.taskList.get(taskNumber).toString();
    }

    /**
     * Return string representation of task list to be stored in storage.
     * @return string representation of task list.
     */
    public String toFileData() {
        String fileString = "";
        for (Task task : this.taskList) {
            fileString = fileString + task.toFileData() + "\n";
        }
        return fileString;
    }

    /**
     * Returns string representation of task list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            str = str + String.format("\t   %d. %s", i + 1, task.toString()) + "\n";
        }
        return str;
    }
}
