package duke;
import java.util.ArrayList;

/**
 * A TaskList class that represent the lists of tasks inputted by user
 */
public class TaskList {
    //ArrayList to store tasks
    private ArrayList<Task> lst;

    /**
     * Default constructor for TaskList class
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Other constructor for TaskList class
     * @param lst An ArrayList of the tasks inputted by user
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * A method that returns the list of tasks inputted by user in the form of an ArrayList
     * @return An ArrayList of Task
     */
    public ArrayList<Task> getTasks() {
        return lst;
    }

    /**
     * A method that returns a specific task corresponding to the index specified
     * @param i index of task in the ArrayList
     * @return A Task object
     */
    public Task getTask(int i) {
        return lst.get(i);
    }

    /**
     * A method that returns the number of tasks in the ArrayList (size of ArrayList)
     * @return An integer representing the size of ArrayList
     */
    public int getSize() {
        return lst.size();
    }

    /**
     * A method that adds the specified task into the ArrayList
     * @param task Task object to be added to the ArrayList
     */
    public void addTask(Task task) {
        lst.add(task);
    }

    /**
     * A method that marks the task specified by the index
     * @param index An int representing the index of task to be marked
     * @throws DukeException
     */
    public void markTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeMarked = lst.get(index);
            taskToBeMarked.markAsDone();
        }
    }

    /**
     * A method that unmarks the task specified by the index
     * @param index An int representing the index of task to be unmarked
     * @throws DukeException
     */
    public void unmarkTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeUnmarked = lst.get(index);
            taskToBeUnmarked.markAsUndone();
        }
    }

    /**
     * A method that deletes the task specified by the index
     * @param index An int representing the index of task to be deleted
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException {
        int size = lst.size();
        if (index >= size || index < 0) {
            throw new DukeException("OOPS! The index of the task does not exists.");
        } else {
            Task taskToBeDeleted = lst.get(index);
            lst.remove(index);
            return taskToBeDeleted;
        }
    }
}
