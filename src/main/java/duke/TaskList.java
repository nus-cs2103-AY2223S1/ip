package duke;

import java.util.ArrayList;

/**
 * Represents a list of task by storing a list of <code>Task</code> objects.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty <code>TaskList</code>.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a <code>TaskList</code> initialized with the specified list of tasks.
     *
     * @param tasks A list of tasks to be managed by this <code>TaskList</code>.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task into this <code>TaskList</code>.
     *
     * @param task The <code>Task</code> object to be added into this <code>TaskList</code>.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the task in the specified index.
     *
     * @param index Index of the task.
     * @return The <code>Task</code> in the specified index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of <code>Task</code> in this <code>TaskList</code>.
     *
     * @return The number of <code>Task</code> in this <code>TaskList</code>.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks the <code>Task</code> in the specified index to be done.
     *
     * @param index Index of the <code>Task</code> to be marked done.
     */
    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the <code>Task</code> in the specified index to be not done.
     *
     * @param index Index of the <code>Task</code> to be marked not done.
     */
    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    /**
     * Removes the <code>Task</code> in the specified index.
     *
     * @param index Index of the <code>Task</code>.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Prints out the list of <code>Task</code> stored in this <code>TaskList</code>.
     */
    public String list() {
        Task[] x = new Task[tasks.size()];
        Task[] tasksArray = tasks.toArray(x);
        String response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasksArray.length; i++) {
            Task task = tasksArray[i - 1];
            response += i + "." + task.toString() + "\n";
        }
        return response;
    }

    /**
     * Prints the <code>Task</code>s that matches the given input.
     *
     * @param input Keyword of the description of the <code>Task</code>.
     */
    public String find(String input) {
        String response = "Here are the matching tasks in your list:\n";
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            if (task.contains(input.substring(5))) {
                response += count + "." + task + "\n";
                count++;
            }
        }
        return response;
    }

}
