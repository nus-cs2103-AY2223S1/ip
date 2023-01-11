package duke;

import java.util.ArrayList;
import java.util.Locale;

/**
 * TaskList stores the tasks of the user.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList.
     *
     * @param tasks The list of tasks the user has.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getCount() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i index in the list.
     * @return Task at specified index.
     */
    public Task get(int i) {
        assert tasks.size() > i && i >= 0 : i;
        return this.tasks.get(i);
    }

    /**
     * Adds task to list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        assert t != null;
        tasks.add(t);
    }

    /**
     * Removes task at specified index from the list.
     *
     * @param i Index to remove task.
     * @return Task removed from list.
     */
    public Task remove(int i) {
        assert tasks.size() > i && i >= 0 : i;
        return tasks.remove(i);
    }

    /**
     * Finds tasks in the list that match the input string.
     *
     * @param input String to match.
     * @return List of tasks that match the input.
     */
    public ArrayList<Task> find(String input) {
        ArrayList toReturn = new ArrayList<>();
        String inputLower = input.toLowerCase();
        for(Task t : tasks) {
            String taskNameLower = t.getTaskName().toLowerCase();
            if(taskNameLower.contains(inputLower)) {
                toReturn.add(t);
            }
        }
        return toReturn;
    }

    /**
     * Clears the tasklist.
     */
    public void clear() {
        tasks.clear();
    }

}

