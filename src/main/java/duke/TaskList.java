package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * List of Tasks recorded by the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class TaskList {

    private ArrayList<Task> taskArrayList;

    /**
     * Constructs a TaskList when there is no previous saved data.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>(100);
    }

    /**
     * Constructs a TaskList based on previously saved data.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Prints the list of tasks that are recorded.
     */
    public void printList() {
        ArrayList<Task> list = this.taskArrayList;
        list.forEach(t -> System.out.println(list.indexOf(t) + 1 + ". " + list.get(list.indexOf(t)).toString()));
    }

    /**
     * Adds a new task to the list.
     *
     * @param t New task to be added to the list.
     */
    public void add(Task t) {
        this.taskArrayList.add(t);
    }

    /**
     * Deletes a task from the list.
     *
     * @param i Index of the task in the list to be deleted.
     */
    public void delete(int i) {
        this.taskArrayList.remove(i - 1);
    }

    /**
     * Marks a task from the list as done.
     *
     * @param i Index of the task in the list to be marked.
     */
    public void mark(int i) {
        ArrayList<Task> list = this.taskArrayList;
        Task t = list.get(i - 1);
        t.mark();
    }

    /**
     * Unmarks a task from the list as done.
     *
     * @param i Index of the task in the list to be unmarked.
     */
    public void unmark(int i) {
        ArrayList<Task> list = this.taskArrayList;
        Task t = list.get(i - 1);
        t.unmark();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    /**
     * Finds tasks in the list that contains the specified input String.
     *
     * @param s Input string to be checked against.
     */
    public void find(String s) {
        int counter = 1;
        ArrayList<Task> list = this.taskArrayList;
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.toString().contains(s)) {
                System.out.println(counter + ". " + t);
                counter++;
            }
        }
    }

}
