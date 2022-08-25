package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList is a class that contains the task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for duke.TaskList, which stores task entered by user in duke.Duke.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for duke.TaskList, which stores task entered by user in duke.Duke.
     * @param taskList ArrayList containing task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Returns the task list.
     * @return task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to taskList.
     * @param task task to be added to taskList.
     */
    public void addToTaskList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes task from taskList.
     * @param taskIndex index of task to be removed.
     */
    public void removeFromTaskList(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
    }

    /**
     * Returns size of taskList.
     * @return size of taskList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns task in taskList at specified index.
     * @param index index of task in taskList.
     * @return task in taskList at specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

}
