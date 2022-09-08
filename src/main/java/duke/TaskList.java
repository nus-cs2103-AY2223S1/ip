package duke;

import java.util.ArrayList;

/**
 * A custom class to keep track of the tasks to be done
 *
 * @author eugeneleong
 * @version 1.0
 */

public class TaskList {

    private static final String LINE = "-----------------------------";
    private ArrayList<Task> tasks;

    /**
     * If there are no pre-existing Tasklist, create a new Tasklist
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with pre-defined tasks
     * @input pre-existing list of tasks
     */
    public TaskList(ArrayList<Task> ls) {
        this.tasks = ls;
    }
    /**
     * Finds all the tasks which contain the specified keyword and prints them out
     * @input keyword to check
     * @return found task
     */
    public String findTask(String keyword) {
        ArrayList<Task> matched = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list:");
        for (Task t : tasks) {
            if (t.getAction().contains(keyword)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            return "Boo! Your task that you are searching for cannot be found :(";
        } else {
            for (int j = 0; j < matched.size(); j++) {
                Task task = matched.get(j);
                output.append(j + 1).append(".").append(task);
            }
            return output.toString();
        }
    }

    /**
     * Returns the task at the specified index of the list
     * @param idx index of task in TaskList
     * @return required task at the index
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public int getSize() {
        return tasks.size();
    }
    /**
     * Marks a task (with a specified index) as "Done"
     */
    public void mark(int idx) {
        Task task = tasks.get(idx);
        task.markIsDone();
    }

    /**
     * Marks a task (with a specified index) as "Undone"
     */
    public void unmark(int idx) {
        Task task = tasks.get(idx);
        task.unmarkIsDone();
    }

    /**
     * Adds a task into TaskList
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * @input index of task to be removed from TaskList
     */
    public void delete(int idx) {
        tasks.remove(idx);
    }

    /**
     * Prints out all the tasks in the current TaskList
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            output.append(i + 1).append(".").append(t).append("\n");
        }
        return output.toString();
    }
}
