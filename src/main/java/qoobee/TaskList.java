package qoobee;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private Storage storage;
    private List<Task> taskList;

    /**
     * Creates a tasklist given a storage.
     * @param storage The object where the tasklist will be stored to.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = storage.getList();
    }

    /**
     * Returns the size of the tasklist.
     * @return The size of the tasklist.
     */
    public int taskListSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task given an index inputted.
     * @param index The index of the task identified.
     * @return Returns a task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Prints the list of tasks.
     */
    public void printTasks() {
        if (taskListSize() == 0) {
            System.out.println("You have no tasks dummy!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskListSize(); i++) {
                Task currentTask = taskList.get(i);
                System.out.println((i + 1) + "." + currentTask);
            }
        }
    }

    /**
     * Adds a task into the list.
     * @param task A task to be added in the list.
     * @throws QoobeeException if the user inputs an invalid syntax.
     */
    public void addTask(Task task) throws QoobeeException {
        this.taskList.add(task);
        System.out.println("Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + taskListSize() + " tasks in the list.");
        storage.save(taskList);
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task identified.
     * @throws QoobeeException if the task does not exist.
     */
    public void removeTask(int index) throws QoobeeException {
        try {
            Task task = taskList.remove(index);
            System.out.println("Noted. I've removed this task:\n" + task + "\n"
                    + "Now you have " + taskListSize() + " tasks in the list.");
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new QoobeeException("Please enter a right number!");
        }
    }

    /**
     * Unmarks the task as undone.
     * @param task The task to be unmarked.
     * @throws QoobeeException if the task does not exist.
     */
    public void unmark(Task task) throws QoobeeException {
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        storage.save(taskList);
    }

    /**
     * Marks a task as done.
     * @param task The task to be marked.
     * @throws QoobeeException if the tast does not exist.
     */
    public void mark(Task task) throws QoobeeException {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        storage.save(taskList);
    }

    /**
     * Finds a task in the list
     * @param description The description of the task
     */
    public void findTask(String description) {
        List<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskListSize(); i++) {
            Task curr = taskList.get(i);
            if (taskList.get(i).getDescription().contains(description)) {
                foundTasks.add(curr);
            }
        }
        if (foundTasks.size() == 0) {
            System.out.println("No such task!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                Task currentTask = foundTasks.get(i);
                System.out.println((i + 1) + "." + currentTask);
            }
        }
    }

}
