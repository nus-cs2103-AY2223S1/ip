package duke;

import java.util.ArrayList;

/**
 * TaskList is a class that represents a list of Tasks
 */
public class TaskList {
    private ArrayList<Task> lst;

    /**
     * Constructor for TaskList, initialized with an empty ArrayList
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param lst ArrayList to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public ArrayList<Task> getTaskArr() {
        return this.lst;
    }


    /**
     * Prints into console the current Tasks in the List in a nice numbered format.
     */
    public void showList() {
        if (!lst.isEmpty()) {
            for (int i = 0; i < lst.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + lst.get(i));
            }
        } else { // list is empty
            Ui.show("duke.Task list is empty!");
        }
    }

    /**
     * Returns true if the list is empty.
     *
     * @return Returns true if the list is empty.
     */
    public boolean isEmpty() {
        return lst.isEmpty();
    }

    /**
     * Returns the Task at the specified position in this list.
     *
     * @param index index of the element in the list.
     * @return Returns the Task at the specified position in this list.
     */
    public Task get(int index) {
        return lst.get(index);
    }

    /**
     * Appends the specified Task to the end of this list.
     *
     * @param t The Task to be added
     */
    public void add(Task t) {
        lst.add(t);
    }

    /**
     * Returns the number of Tasks in the list.
     *
     * @return Returns the number of Tasks in the list.
     */
    public int size() {
        return lst.size();
    }

    /**
     * Removes the Task at the specified index.
     *
     * @param index Index to remove the Task.
     */
    public void remove(int index) {
        lst.remove(index);
    }

    public TaskList find(String s) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task t : lst) {
            if (t.toString().contains(s)) {
                res.add(t);
            }
        }
        return new TaskList(res);
    }
}
