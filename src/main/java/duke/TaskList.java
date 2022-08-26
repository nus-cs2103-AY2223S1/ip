package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     * @param tasks An ArrayList object containing Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Another constructor for the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the ArrayList object that contains Task objects.
     * @return The instance field 'tasks' that is an ArrayList object.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes a task from the ArrayList object 'tasks'.
     * @param taskNumber An integer that provides the index of the task in the ArrayList to be removed.
     */
    public void deleteTask(int taskNumber) {
        Task t = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("     Ok! I have removed the following task!:\n"
                    + "       " + t.toString() + "\n"
                    + "     You now have a total of " + tasks.size() + " tasks!");
    }

    /**
     * Adds a task to the ArrayList object 'tasks'.
     * @param t The Task object to be added into the ArrayList object.
     */
    public void addTask(Task t) {
        tasks.add(t);
        System.out.println("     Ok! I have added the following "
                    + ((t instanceof Todo)
                        ? "Todo "
                        : (t instanceof Event)
                        ? "Event "
                        : "Deadline ")
                    + "task!:\n"
                    + "       " + t.toString() + "\n"
                    + "     You now have a total of " + tasks.size() + " tasks!");
    }

    public static void main(String[] args) {
        TaskList tl = new TaskList();
        Task t = new Todo("read books");
        tl.addTask((t));
        tl.addTask(new Deadline("return books", "2022-10-28 18:00"));
        tl.addTask(new Event("attend wedding", "2022-12-20"));
    }
}
