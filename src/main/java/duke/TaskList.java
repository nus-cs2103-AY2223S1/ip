package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks stored in an ArrayList of tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String countTasks() {
        return "\nYou have " + tasks.size() + " tasks in the list now";
    }

    /**
     * Adds a task without deadline to the list.
     *
     * @param desc Task description.
     * @return Associated message from Duke.
     */
    public String addTodo(String desc) {
        Todo t = new Todo(desc);
        tasks.add(t);
        return "Successfully added: " + t + countTasks();
    }

    /**
     * Adds a task with deadline to the list.
     *
     * @param desc Task description.
     * @param deadline Deadline in LocalDate format.
     * @return Associated message from Duke.
     */
    public String addDeadline(String desc, LocalDate deadline) {
        Deadline d = new Deadline(desc, deadline);
        tasks.add(d);
        return "Successfully added: " + d + countTasks();
    }

    /**
     * Adds a task with deadline to the list.
     *
     * @param desc Task description.
     * @param deadline Deadline in String format.
     * @return Associated message from Duke.
     */
    public String addDeadline(String desc, String deadline) {
        Deadline d = new Deadline(desc, deadline);
        tasks.add(d);
        return "Successfully added: " + d + countTasks();
    }

    /**
     * Adds an event to the list.
     *
     * @param desc Event description.
     * @param time Event time in LocalDate format.
     * @return Associated message from Duke.
     */
    public String addEvent(String desc, LocalDate time) {
        Event e = new Event(desc, time);
        tasks.add(e);
        return "Successfully added: " + e + countTasks();
    }

    /**
     * Adds an event to the list.
     *
     * @param desc Event description.
     * @param time Event time in String format.
     * @return Associated message from Duke.
     */
    public String addEvent(String desc, String time) {
        Event e = new Event(desc, time);
        tasks.add(e);
        return "Successfully added: " + e + countTasks();
    }

    /**
     * Prints the list of tasks.
     *
     * @return List of tasks.
     */
    public String listToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        return sb + countTasks();
    }

    /**
     * Deletes a task from the list.
     *
     * @param i Index of the task in the list.
     * @return Associated message from Duke.
     */
    public String deleteTask(int i) {
        Task t = tasks.get(i);
        tasks.remove(t);
        return "Successfully deleted: " + t + countTasks();
    }

    /**
     * Marks a task as completed.
     *
     * @param i Index of task to be marked as completed.
     * @return Associated message from Duke.
     */
    public String markTask(int i) {
        Task t = this.tasks.get(i);
        return t.markTask();
    }

    /**
     * Marks a task as not completed.
     *
     * @param i Index of task to be marked as not completed.
     * @return Associated message from Duke.
     */
    public String unmarkTask(int i) {
        Task t = this.tasks.get(i);
        return t.unmarkTask();
    }

    /**
     * Prints a list of tasks containing the string.
     *
     * @param query String to find tasks with the matching string.
     * @return List of tasks with matching string.
     */
    public String findTask(String query) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            if (t.getDescription().contains(query)) {
                count++;
                sb.append(count + ". " + t + "\n");
            }
        }
        return sb + "There are " + count + " tasks that contains: " + query;
    }
}
