package duke;

import tasks.Task;

import java.util.ArrayList;

/**
 * List storing all the Task objects
 */
public class TaskList {

    /**
     * An arraylist that contains all the tasks
     */
    public ArrayList<Task> lst;

    /**
     * A constructor that initializes a new tasklist
     */
    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    /**
     * A constructor that initializes an existing tasklist
     *
     * @param lst the tasklist stored in an arraylist
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * A method that returns the number of tasks
     *
     * @return int The number of tasks in a tasklist
     */
    public int count() {
        return lst.size();
    }

    /**
     * A method that returns a specific task in the tasklist
     *
     * @param index The index of the task
     * @return Task The task corresponding to the index
     */
    public Task getTask(int index) {
        return this.lst.get(index);
    }

    /**
     * A method that marks a specific task in the tasklist
     *
     * @param index The index of the task
     * @return Task The newly marked task
     */
    public Task markTask(int index) {
        Task t = this.lst.get(index);
        t.mark();
        return t;
    }

    /**
     * A method that unmarks a specific task in the tasklist
     *
     * @param index The index of the task
     * @return Task The newly unmarked task
     */
    public Task unmarkTask(int index) {
        Task t = this.lst.get(index);
        t.unmark();
        return t;
    }

    /**
     * A method that adds a task into the tasklist
     *
     * @param t The task
     */
    public void addTask(Task t) {
        this.lst.add(t);
    }

    /**
     * A method that deletes a task into the tasklist
     *
     * @param t The task
     */
    public void deleteTask(Task t) {
        this.lst.remove(t);
    }

    /**
     * A method that returns a task based on a keyword
     *
     * @param keyword The keyword
     * @return Task The task with the keyword
     */
    public ArrayList<Task> filterTask(String keyword) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (Task t: lst) {
            if (t.description.contains(keyword)) {
                allTasks.add(t);
            }
        }
        return allTasks;
    }
}
