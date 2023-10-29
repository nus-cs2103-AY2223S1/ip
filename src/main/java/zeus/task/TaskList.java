package zeus.task;

import java.util.ArrayList;

import zeus.ZeusException;

/**
 * Represents a list of tasks.
 *
 * @author Derrick Khoo
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs the list of tasks entered from a previous session that
     * is captured in the hard disk.
     *
     * @param taskList the list of tasks that was captured in the hard disk
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * A getter for the number of <code>Task</code> in the list of tasks.
     *
     * @return an integer representing the number of tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * A getter for the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * The method to add a task to the list of tasks.
     *
     * @param t the task to be added
     * @throws ZeusException if there is an error adding the task to the list of tasks
     */
    public void addTask(Task t) throws ZeusException {
        this.tasks.add(t);
    }

    /**
     * The method to delete a task from the list of tasks.
     *
     * @param index the index of the task, using 1-based indexing from user perspective
     * @return the task that is deleted from the list of tasks
     * @throws ZeusException if there is an error deleting the task
     */
    public Task deleteTask(int index) throws ZeusException {
        int numOfTasks = this.tasks.size();
        if (numOfTasks == 0) {
            throw new ZeusException("You cant delete anything yet! Try creating some tasks first!");
        }
        if (index < 1) {
            throw new ZeusException("Hey there! Are you sure you are referring to a correct task? "
                    + " It definitely has to be at least 1!");
        }
        if (index > numOfTasks) {
            throw new ZeusException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        assert index <= numOfTasks;
        Task t = getTask(index);
        int indexInList = index - 1;
        this.tasks.remove(indexInList);
        return t;
    }

    /**
     * The method to retrieve a <code>Task</code> from the list of tasks.
     *
     * @param index the index of the task within the list of tasks,
     *              using 1-based indexing from user perspective
     * @return the task that is queried
     * @throws ZeusException if there is an error accessing the <code>Task</code> from
     *      the list of tasks
     */
    public Task getTask(int index) throws ZeusException {
        int numOfTasks = this.tasks.size();
        if (numOfTasks == 0) {
            throw new ZeusException("Unfortunately, you do not have any tasks at hand."
                    + " Try creating some first.");
        }
        if (index > numOfTasks) {
            throw new ZeusException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (index < 1) {
            throw new ZeusException("Hey there! Are you sure you are referring to a correct task? "
                    + "It definitely has to be at least 1!");
        }
        assert index <= numOfTasks;
        return this.tasks.get(index - 1);
    }

    /**
     * Returns a message which denotes the number of <code>Task</code> in the list of tasks, to the user.
     * @return message which denotes number of Task in the list of tasks
     */
    public String getArraySize() {
        return "Now you have " + this.tasks.size() + " tasks in the list.";
    }
}
