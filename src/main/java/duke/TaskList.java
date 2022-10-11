package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Class to store list of tasks
 */
public class TaskList {
    private ArrayList<Task> lst;

    /**
     * Constructor for new TaskList
     */
    TaskList() {

        this.lst = new ArrayList<Task>();
    }

    /**
     * Constructor for list of tasks
     * @param lst previous list stored in storage file
     */
    TaskList(ArrayList<String> lst) {
        //parse duke.task from string to duke.task
        this.lst = new ArrayList<Task>();
        for (int i = 0; i < lst.size(); i++) {
            this.lst.add((Task.parseTask(lst.get(i))));
        }
    }

    /**
     * Adds new task to the Tasklist
     * @param task Task to add
     * @param storage Storage to be updated
     */
    public void addNewTask(Task task, Storage storage) {
        this.lst.add(task);
        updateStorage(storage);
    }

    /**
     * Deletes task with specified index from the TaskList
     * @param index Index of task to be deleted
     * @param storage Storage to be updated
     * @return String for user response
     * @throws DukeException If index specified is out of bounds
     */
    public String deleteTask(int index, Storage storage) throws DukeException {
        if (index > lst.size() - 1 || index < 0) {
            throw new DukeException("There is no such task");
        }
        Task t = lst.get(index);
        this.lst.remove(index);
        updateStorage(storage);
        return("Noted. I've removed this task: \n" + t.formatTask() + "\nNow you have "
                + lst.size() + " tasks in the list.");
    }

    /**
     * Getter method for size of TaskList
     * @return size of TaskList
     */
    public int size() {
        return this.lst.size();
    }

    /**
     * Getter method of the TaskList
     * @param index Index of task
     * @return Task specified by index
     */
    public Task get(int index) {
        return this.lst.get(index);
    }

    /**
     * Marks task in the TaskList
     * @param index Index of task to be marked
     * @param storage Storage to be updated
     * @return String for user response
     */
    public String markTask(int index, Storage storage) {
        Task t = lst.get(index);
        t.markAsDone();
        lst.set(index, t);
        updateStorage(storage);
        return ("Nice! I've marked this task as done: \n" + lst.get(index).formatTask());

    }

    /**
     * Unmarks task in the TaskList
     * @param index Index of task to be unmarked
     * @param storage Storage to be updated
     * @return String for user response
     */
    public String unmarkTask(int index, Storage storage) {
        Task t = lst.get(index);
        t.unMark();
        lst.set(index, t);
        updateStorage(storage);
        return ( "OK, I've marked this task as not done yet:\n" + lst.get(index).formatTask());

    }

    /**
     * Updates storage when changes are made to the TaskList
     * @param storage Storage to be updated
     */
    public void updateStorage(Storage storage) {
        System.out.println(lst.size());
        if (lst.size() == 0) {
            try {
                storage.update(lst);
            } catch (IOException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
        for (int i = 0; i < lst.size(); i++) {
            try {
                storage.update(lst);
            } catch (IOException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
    }

    /**
     * Finds task with given keyword in the TaskList
     * @param toFind keyword specified by user
     * @return List of tasks with given keyword
     */
    public ArrayList<String> findTasks(String toFind) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).match(toFind)) {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }

    /**
     * Finds all Todo tasks in the TaskList
     * @return List of tasks in Todo category
     */
    public ArrayList<String> findTodo() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
            if (lst.get(i).toString().charAt(0) == 'T') {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }

    /**
     * Finds all Event tasks in the TaskList
     * @return List of tasks in Event category
     */
    public ArrayList<String> findEvent() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
            if (lst.get(i).toString().charAt(0) == 'E') {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }

    /**
     * Finds all Deadline tasks in the TaskList
     * @return List of tasks in Deadline category
     */
    public ArrayList<String> findDeadline() {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).toString().charAt(0) == 'D') {
                result.add(lst.get(i).formatTask());
            }
        }
        return result;
    }
}
