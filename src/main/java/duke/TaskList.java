package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList is a class that contains the task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for duke.TaskList, which stores task entered by user in duke.Duke.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor for duke.TaskList, which stores task entered by user in duke.Duke.
     * @param taskList ArrayList containing task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the task list.
     * @return task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds task to taskList.
     * @param task task to be added to taskList.
     */
    public void addToTaskList(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes task from taskList.
     * @param taskIndex index of task to be removed.
     */
    public void removeFromTaskList(int taskIndex) {
        this.taskList.remove(taskIndex - 1);
    }

    /**
     * Returns size of taskList.
     * @return size of taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns task in taskList at specified index.
     * @param index index of task in taskList.
     * @return task in taskList at specified index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

}
