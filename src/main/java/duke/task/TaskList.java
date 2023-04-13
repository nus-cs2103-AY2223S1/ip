package duke.task;

import duke.exceptions.TaskNotFoundException;

import java.util.ArrayList;

/**
 * Represents a list of tasks
 * @author Nam Minh Quan
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }
    /**
     * Returns all tasks in the list
     */
    public ArrayList<Task> listTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns length of the task list
     *
     * @return length of the task list
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Adds a new task into the list
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a Duke task in the list as done
     *
     * @param index index of the Duke task to be marked as done
     * @return the task marked
     */
    public Task mark(int index) throws TaskNotFoundException {
        try {
            Task temp = tasks.get(index - 1);
            temp.mark();
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("This task cannot be found in our list! Check your index!");
        }
    }
    /**
     * Marks a Duke task in the list as not done
     *
     * @param index index of the Duke task to be unmarked
     * @return the task unmarked
     */
    public Task unmark(int index) throws TaskNotFoundException {
        try {
            Task temp = tasks.get(index - 1);
            temp.unmark();
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("This task cannot be found in our list! Check your index!");
        }
    }

    /**
     * Deletes a Duke task from the task list
     *
     * @param index index of the Duke.Duke.task to be deleted
     * @return the task deleted
     */
    public Task delete(int index) throws TaskNotFoundException {
        try {
            Task temp = tasks.get(index - 1);
            tasks.remove(index - 1);
            return temp;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("This task cannot be found in our list! Check your index!");
        }
    }

    /**
     * Returns an ArrayList of Task objects that contains
     * the searchItem string in the description
     *
     * @param searchItem keyword used for searching
     * @return ArrayList of Task
     */
    public ArrayList<Task> find(String searchItem) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task task : tasks) {
            String description = task.getDescription();
            if (description.contains(searchItem)) {
                result.add(task);
            }
        }
        return result;
    }
}
