package zeus.main;

import java.util.ArrayList;

import zeus.task.Task;


/**
 * Class that represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor of Tasklist class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns current size of task list.
     *
     * @return size of task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Getter that returns the Task at the given index in the task list.
     *
     * @param idx index of Task
     * @return Task at the specific index in the task list
     */
    public Task getTask(int idx) {
        return this.taskList.get(idx);
    }

    /**
     * Getter that returns the list of tasks.
     *
     * @return an ArrayList containing the tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to task list
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a Task at given index from the task list.
     *
     * @param idx Index of Task to be removed
     */
    public void removeTask(int idx) {
        taskList.remove(idx);
    }

    /**
     * Sets the task at the given index as done.
     * @param idx Index of target Task
     */
    public void setTaskDone(int idx) {
        this.taskList.get(idx).markAsDone();
    }

    /**
     * Sets the task at the given index as not done.
     * @param idx index of target Task
     */
    public void setTaskNotDone(int idx) {
        this.taskList.get(idx).markAsNotDone();
    }
}
