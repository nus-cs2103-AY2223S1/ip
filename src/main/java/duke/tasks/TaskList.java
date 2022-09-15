package duke.tasks;

import java.util.ArrayList;

/*
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /*
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /*
     * Constructs a TaskList object.
     * 
     * @param list The list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /*
     * Adds a task to the list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /*
     * Removes a task from the list.
     */
    public void delete(int index) {
        list.remove(index);
    }

    /*
     * Returns the task at the specified index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /*
     * Returns the size of the list.
     * 
     * @return The size of the list.
     */
    public int size() {
        return list.size();
    }

    /*
     * Returns the list of tasks.
     * 
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /*
     * Returns a list of tasks that contain the specified keyword.
     * 
     * @param keyword The keyword to be searched.
     * 
     * @return The list of tasks that contain the specified keyword in their
     * description.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> found = new ArrayList<Task>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                found.add(task);
            }
        }
        return found;
    };

    /*
     * Returns a string representation of the list of tasks.
     * 
     * @return A string representation of the list of tasks.
     */
    public String toString() {
        if (list.size() == 0) {
            return "You have no tasks in your list.";
        }
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return result;
    }
}
