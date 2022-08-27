package duke;

import java.util.ArrayList;

/**
 * Used to represent all the Tasks
 * to be done as it
 * contains a list of all tasks
 */
public class TaskList {
    private ArrayList<Task> lst;


    TaskList(ArrayList<Task> lst) {

        this.lst = lst;
    }

    TaskList() {
        lst = new ArrayList<>();
    }

    /**
     * Returns the task in specified index
     * @param ind index of task
     * @return Task at index ind
     */
    public Task get(int ind) {
        return lst.get(ind);

    }

    /**
     * Returns ArrayList of all tasks that are currently in Tasklist
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.lst;
    }

    /**
     * Removes a task from the list if not
     * needed anymore
     * @param task Task that needs to be removed
     */
    public void delete(Task task) {
        System.out.println("Noted I have removed this task");
        System.out.println(task);
        lst.remove(task);
        System.out.println("Now you have" + " " + lst.size() + " " + "tasks in list");
    }

    /**
     * Adds a task to the list
     * @param task Task that needs to be added
     */
    public void add(Task task) {
        System.out.println("Got it.I've added this task");
        lst.add(task);
        System.out.println(task);
        System.out.println("Now you have" + " " + lst.size() + " " + "tasks in list");
    }

    /**
     * Marks a test to indicate it as done
     * @param task Task to be marked
     */
    public void mark(Task task) {
        System.out.println("Nice I have marked this as done:");
        task.mark();
        System.out.println(" " + task);
    }

    /**
     * Mark a test to indicate it as still need
     * to be done
     * @param task Task to be unmarked
     */
    public void unmark(Task task) {
        System.out.println("Ok I have marked this as still to be done:");
        task.unmark();
        System.out.println(" " + task);
    }

    /**
     * Prints out all tasks already in TaskList
     * and lists them in order
     */
    public void list() {
        System.out.println("This is your tasks in your list: \n");
        for (Task item : lst) {
            if (item != null)
                System.out.println(lst.indexOf(item) + 1 + "." + item);
        }
    }
}
