package arc;

import java.util.ArrayList;

/**
 * Encapsulates a list of Tasks
 */
public class TaskList {

    private ArrayList<Task> arrayList;

    /**
     * Constructor for TaskList
     * @param arrayList An ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    /**
     * Returns the number of tasks currently in TaskList
     * @return The number of tasks currently in TaskList
     */
    public int numTasks() {
        return this.arrayList.size();
    }

    /**
     * Gets the tasks at the given index
     * @param index The index of the task to retrieve
     * @return The task at the given index
     */
    public Task getTask(int index) {
        return arrayList.get(index);
    }

    /**
     * Prints out all Tasks currently in TaskList
     */
    public void listTasks() {
        System.out.println("Listing the tasks in your list...");

        if (this.arrayList.size() == 0) {
            System.out.println("You have no current tasks :-(");
        }

        for (int i = 0; i < this.arrayList.size(); i++) {
            System.out.println(String.format("\t%d. %s", i + 1, this.arrayList.get(i)));
        }
    }

    /**
     * Adds a new aRC.Task to arraylist
     * @param newTask The new aRC.Task to be added
     */
    public void addTask(Task newTask) {
        this.arrayList.add(newTask);
        System.out.println(String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d task%s in the list.",
                newTask, this.arrayList.size(), this.arrayList.size() == 1 ? "" : "s"));
    }

    /**
     * Deletes a aRC.Task from arraylist
     * @param index The index of the aRC.Task to be deleted
     */
    public void deleteTask(int index) {
        Task deletedTask = this.arrayList.remove(index);
        System.out.println(String.format(
                "Noted. I've removed this task:\n\t%s\nNow you have %d task%s in the list.",
                deletedTask, this.arrayList.size(), this.arrayList.size() == 1 ? "" : "s"));
    }
}
