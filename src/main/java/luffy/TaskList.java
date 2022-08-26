package luffy;

import java.util.ArrayList;

/**
 * TaskList class to handle tasks.
 * @author Silas Tay (A0233425M)
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList with content.
     * @param tasks Tasks to fill the TaskList with
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets Task at index of TaskList
     * @param index Index of Task
     * @return Task at index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns size of ArrayList.
     * @return Size of arrayList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds Task to TaskList.
     * @param task Task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it, I've added this task:");
        System.out.println("  " + task.toString());
    }

    /**
     * Deletes Task at index.
     * @param index Index of Task to be deleted
     */
    public void delete(int index) {
        Task deletedTask = this.tasks.get(index);
        this.tasks.remove(index);
        ui.printMessage("Noted. I've removed this task:");
        ui.printMessage("  " + deletedTask);
    }

    /**
     * Marks Task at index as completed.
     * @param index Index of Task to be marked completed
     */
    public void markCompleted(int index) {
        tasks.get(index).markCompleted();
        ui.printMessage("Nice! I've marked this task as done:");
        ui.printMessage("  " + tasks.get(index));
    }

    /**
     * Marks Task at index as uncompleted.
     * @param index Index of Task to be marked uncompleted
     */
    public void markUncompleted(int index) {
        tasks.get(index).markUncompleted();
        ui.printMessage("Alright! I've marked this task as undone:");
        ui.printMessage("  " + tasks.get(index));
    }

    /**
     * Get Queried TaskList.
     *
     * @param query String Query
     * @return Queried TaskList
     */
    public TaskList getQueriedTaskList(String query) {
        ArrayList<Task> queriedTasks = new ArrayList<Task>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).doesContain(query)) {
                queriedTasks.add(tasks.get(i));
            }
        }
        return new TaskList(queriedTasks);
    }

    /**
     * Returns String representation of TaskList.
     * @return String representation of TaskList
     */
    public String toString() {
        String returnString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            returnString += (i + 1) + "." + tasks.get(i)
                    + ((i != this.tasks.size() - 1) ? "\n" : "");
        }
        return returnString;
    }
}
