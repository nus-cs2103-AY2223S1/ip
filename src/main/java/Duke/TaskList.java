package duke;

import java.util.ArrayList;

/**
 * TaskList object class that stores ArrayList of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object with empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object with a list of tasks.
     *
     * @param listOfTasks list of tasks to store.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.tasks = listOfTasks;
    }

    /**
     * Adds a Task object to list of tasks in TaskList object.
     *
     * @param task object added to list of tasks.
     */
    public void add(Task task) {
        int initialSize = this.tasks.size();
        this.tasks.add(task);
        int afterSize = this.tasks.size();
        assert (afterSize - initialSize) == 1 : "Task list should increment by 1";
    }

    /**
     * Adds a Task object to list of tasks in TaskList object at specified index.
     *
     * @param task  object added to list of tasks.
     * @param index location of list where task should be added.
     */
    public void addTaskToIndex(Integer index, Task task) {
        int initialSize = this.tasks.size();
        this.tasks.add(index, task);
        int afterSize = this.tasks.size();
        assert (afterSize - initialSize) == 1 : "Task list should increment by 1";
    }

    /**
     * Removes a Task object from list of tasks in TaskList object.
     *
     * @param index index of object removed from list of tasks.
     */
    public void remove(int index) {
        int initialSize = this.tasks.size();
        this.tasks.remove(index - 1);
        int afterSize = this.tasks.size();
        assert (initialSize - afterSize) == 1 : "Task list should increment by 1";
    }

    /**
     * Gets the size of the list of tasks in TaskList object.
     *
     * @return size of the list of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets task at specified index of list of tasks in TaskList object.
     *
     * @param index position of task in list of tasks in TaskList object.
     * @return Task at specified position of list of tasks in TaskList object.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets list of tasks in TaskList object.
     *
     * @return ArrayList of Task representing list of tasks stored.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.tasks;
    }

    /**
     * Marks task at specified index in list of tasks as done.
     *
     * @param index position of task in list of tasks to be marked done.
     */
    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
        assert (this.tasks.get(index).isDone) : "Task should be marked as done";
    }

    /**
     * Marks task at specified index in list of tasks as not done.
     *
     * @param index position of task in list of tasks to be marked not done.
     */
    public void markAsNotDone(int index) {
        this.tasks.get(index).markAsNotDone();
        assert (!this.tasks.get(index).isDone) : "Task should be marked as not done";
    }

    /**
     * Removes last task in the list of tasks and returns it.
     *
     * @return last task in the list of tasks.
     */
    public Task popLastTask() {
        Task lastTask = this.tasks.get(this.tasks.size() - 1);
        this.tasks.remove(this.tasks.size() - 1);
        return lastTask;
    }

    /**
     * Gets task in string format.
     *
     * @param index position of task.
     * @return string form of task.
     */
    public String getTaskAsString(int index) {
        return this.tasks.get(index).toString();
    }
}
