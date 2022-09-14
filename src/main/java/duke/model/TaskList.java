package duke.model;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

/**
 * A list to store the tasks.
 */
public class TaskList {
    private List<Task> taskList;

    private List<Task> stashedTaskList;

    /**
     * An empty constructor for a TaskList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        stashedTaskList = null;
    }

    /**
     * A constructor for a TaskList that uses a list of tasks.
     *
     * @param taskList The list of tasks to initialise a TaskList.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
        stashedTaskList = null;
    }

    /**
     * Returns a Task from the TaskList based on the input task number.
     *
     * @param taskNumber An integer representing the task number.
     * @return A Task with respect to the task number.
     */
    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param task The Task to be added into the TaskList.
     */
    public void add(Task task) throws DukeException {
        updateStashedTaskList();
        taskList.add(task);
        Task.incrementNumOfTasks();
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param taskNumber An integer representing the task number.
     * @return the Task that was deleted.
     */
    public Task delete(int taskNumber) throws DukeException {
        updateStashedTaskList();
        Task task = taskList.remove(taskNumber - 1);
        Task.decrementNumOfTasks();
        return task;
    }

    /**
     * Marks a Task as done.
     *
     * @param taskNumber An integer representing the task number.
     */
    public void mark(int taskNumber) throws DukeException {
        updateStashedTaskList();
        taskList.get(taskNumber - 1).mark();
    }

    /**
     * Marks a Task as not done.
     *
     * @param taskNumber An integer representing the task number.
     */
    public void unmark(int taskNumber) throws DukeException {
        updateStashedTaskList();
        taskList.get(taskNumber - 1).unmark();
    }

    /**
     * Saves the last state of the tasklist for the UndoCommand.
     *
     * @throws DukeException If backing up of tasklist fails.
     */
    public void updateStashedTaskList() throws DukeException {
        try {
            List<Task> newTaskList = new ArrayList<>();

            for (int i = 0; i < this.taskList.size(); i++) {
                Task task = this.taskList.get(i);
                Task newTask = (Task) task.clone();
                newTaskList.add(newTask);
            }
            this.stashedTaskList = newTaskList;
        } catch (CloneNotSupportedException e) {
            throw new DukeException("Error! Backing up of tasklist was unsuccessful!");
        }
    }

    /**
     * Reverts the state of the tasklist to how it was before the last change.
     *
     * @return A boolean representing the success of the revert.
     */
    public boolean revert() {
        if (stashedTaskList == null) {
            return false;
        } else {
            taskList = stashedTaskList;
            stashedTaskList = null;
            Task.setNumOfTasks(taskList.size());
            return true;
        }
    }

    /**
     * Returns a formatted string of a TaskList to be stored in the storage.
     *
     * @return A formatted string of a TaskList for storage.
     */
    public String toStorage() {
        String res = "";
        for (int i = 0; i < taskList.size(); i++) {
            res += taskList.get(i).toStorage();
        }
        return res;
    }

    /**
     * Returns a string representation of a TaskList.
     *
     * @return A string representing a TaskList.
     */
    @Override
    public String toString() {
        String str = "";
        if (taskList.size() == 0) {
            str = "\tDid I fry up the pokedex?! I don't see any tasks!";
        } else {
            str = "\tCharmposter! Here are the tasks in your pokedex:";
            for (int i = 0; i < taskList.size(); i++) {
                str += "\n\t" + (i + 1) + ". " + taskList.get(i);
            }
        }
        return str;
    }
}
