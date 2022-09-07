package arc;

import java.util.ArrayList;

/**
 * Encapsulates a list of Tasks
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     * @param tasks An ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks currently in TaskList
     * @return The number of tasks currently in TaskList
     */
    public int numTasks() {
        return this.tasks.size();
    }

    /**
     * Gets the tasks at the given index
     * @param index The index of the task to retrieve
     * @return The task at the given index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Prints out all Tasks whose title matches keyword
     * @param keyword Keyword of Task
     * @return List of matching Tasks
     */
    public String listTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "Listing the%s tasks in your list...\n", !keyword.equals("") ? " matching" : ""
        ));

        if (this.tasks.size() == 0) {
            sb.append("\tYou have no current tasks :-(");
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            if (containsRegardlessOfCase(this.tasks.get(i).title, keyword)) {
                sb.append(String.format("\t%d. %s\n", i + 1, this.tasks.get(i)));
            }
        }

        return sb.toString();
    }

    private boolean containsRegardlessOfCase(String target, String sequence) {
        return target.toLowerCase().contains(sequence.toLowerCase());
    }

    /**
     * Adds a new aRC.Task to arraylist
     * @param newTask The new aRC.Task to be added
     * @return An output message
     */
    public String addTask(Task newTask) {
        this.tasks.add(newTask);
        return String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d task%s in the list.",
                newTask, this.tasks.size(), this.tasks.size() == 1 ? "" : "s");
    }

    /**
     * Deletes a aRC.Task from arraylist
     * @param index The index of the aRC.Task to be deleted
     * @return An output message
     */
    public String deleteTask(int index) {
        Task deletedTask = this.tasks.remove(index);
        return String.format(
                "Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                deletedTask, this.tasks.size(), this.tasks.size() == 1 ? "" : "s");
    }
}
