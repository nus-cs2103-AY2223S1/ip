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
    public String printTasks() {
        if (taskListSize() == 0) {
            return "You have no tasks dummy!";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskListSize(); i++) {
                Task currentTask = taskList.get(i);
                sb.append((i + 1) + "." + currentTask + "\n");
            }
            return sb.toString();
        }
    }

    /**
     * Adds a task into the list.
     * @param task A task to be added in the list.
     * @throws QoobeeException if the user inputs an invalid syntax.
     */
    public String addTask(Task task) throws QoobeeException {
        assert task.getDescription().length() > 0 : "Task description cannot be empty";
        this.taskList.add(task);
        storage.save(taskList);
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + taskListSize() + " tasks in the list.";
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task identified.
     * @throws QoobeeException if the task does not exist.
     */
    public String removeTask(int index) throws QoobeeException {
        try {
            Task task = taskList.remove(index);
            storage.save(taskList);
            return "Noted. I've removed this task:\n" + task + "\n"
                    + "Now you have " + taskListSize() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new QoobeeException("Please enter a right number!");
        }
    }

    /**
     * Unmarks the task as undone.
     * @param task The task to be unmarked.
     * @throws QoobeeException if the task does not exist.
     */
    public String unmark(Task task) throws QoobeeException {
        assert task.getDescription().length() > 0 : "Task description cannot be empty";
        task.markAsUndone();
        storage.save(taskList);
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Marks a task as done.
     * @param task The task to be marked.
     * @throws QoobeeException if the tast does not exist.
     */
    public String mark(Task task) throws QoobeeException {
        assert task.getDescription().length() > 0 : "Task description cannot be empty";
        task.markAsDone();
        storage.save(taskList);
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Finds a task in the list
     * @param description The description of the task
     */
    public String findTask(String description) {
        assert description.length() > 0 : "Task description to find cannot be empty";
        List<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskListSize(); i++) {
            Task curr = taskList.get(i);
            if (taskList.get(i).getDescription().contains(description)) {
                foundTasks.add(curr);
            }
        }
        if (foundTasks.size() == 0) {
            return "No such task!";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                Task currentTask = foundTasks.get(i);
                sb.append(i + 1 + "." + currentTask + "\n");
            }
            return sb.toString();
        }
    }

}
