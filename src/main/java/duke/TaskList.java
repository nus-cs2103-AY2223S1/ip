package duke;

import java.util.ArrayList;

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
    public Task get(int index) {
        return this.lst.get(index);
    }

    /**
     * A method that marks a specific task in the tasklist
     *
     * @param index The index of the task
     */
    public void markTask(int index) {
        Task t = this.lst.get(index);
        t.mark();
        System.out.println("Nice! I've marked this task as done:\n  [X] " + t.description);
    }

    /**
     * A method that unmarks a specific task in the tasklist
     *
     * @param index The index of the task
     */
    public void unmarkTask(int index) {
        Task t = this.lst.get(index);
        t.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + t.description);
    }

    /**
     * A method that adds a task into the tasklist
     *
     * @param tsk The task
     */
    public void add(Task tsk) {
        this.lst.add(tsk);
    }

    /**
     * A method that deletes a task into the tasklist
     *
     * @param tsk The task
     */
    public void delete(Task tsk) {
        this.lst.remove(tsk);
    }

    /**
     * A method that returns a task based on a keyword
     *
     * @param keyword The keyword
     * @return Task The task with the keyword
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (Task t: lst) {
            if (t.description.contains(keyword)) {
                allTasks.add(t);
            }
        }
        return allTasks;
    }
}
