package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks stored in an ArrayList of tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task without deadline to the list.
     *
     * @param s Task description.
     * @return Associated message from Duke.
     */
    public String addTodo(String s) {
        Todo t = new Todo(s);
        tasks.add(t);
        return "Successfully added: " + t + "\nYou have " + this.tasks.size()
            + " tasks in the list now";
    }

    /**
     * Adds a task with deadline to the list.
     *
     * @param s Task description.
     * @param d Deadline in LocalDate format.
     * @return Associated message from Duke.
     */
    public String addDeadline(String s, LocalDate d) {
        Deadline deadline = new Deadline(s, d);
        tasks.add(deadline);
        return "Successfully added: " + deadline + "\nYou have " + this.tasks.size()
            + " tasks in the list now";
    }

    /**
     * Adds a task with deadline to the list.
     *
     * @param s Task description.
     * @param d Deadline in String format.
     * @return Associated message from Duke.
     */
    public String addDeadline(String s, String d) {
        Deadline deadline = new Deadline(s, d);
        tasks.add(deadline);
        return "Successfully added: " + deadline + "\nYou have " + this.tasks.size()
            + " tasks in the list now";
    }

    /**
     * Adds an event to the list.
     *
     * @param s Event description.
     * @param time Event time in LocalDate format.
     * @return Associated message from Duke.
     */
    public String addEvent(String s, LocalDate time) {
        Event event = new Event(s, time);
        tasks.add(event);
        return "Successfully added: " + event + "\nYou have " + this.tasks.size()
            + " tasks in the list now";
    }

    /**
     * Adds an event to the list.
     *
     * @param s Event description.
     * @param time Event time in String format.
     * @return Associated message from Duke.
     */
    public String addEvent(String s, String time) {
        Event event = new Event(s, time);
        tasks.add(event);
        return "Successfully added: " + event + "\nYou have " + this.tasks.size()
            + " tasks in the list now";
    }

    /**
     * Prints the list of tasks.
     *
     * @return List of tasks.
     */
    public String getList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + "." + tasks.get(i) + "\n");
        }
        return sb + "\nYou have " + this.tasks.size()
            + " tasks in the list now";
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task in the list.
     * @return Associated message from Duke.
     */
    public String deleteTask(int index) {
        try {
            Task t = tasks.get(index);
            tasks.remove(t);
            return "Successfully deleted: " + t + "\nYou have " + this.tasks.size()
                    + " tasks in the list now";
        } catch (IndexOutOfBoundsException e) {
            return "I cannot delete a task that does not exist!";
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param i Index of task to be marked as completed.
     * @return Associated message from Duke.
     */
    public String markTask(int i) {
        try {
            Task t = this.tasks.get(i);
            return t.markTask();
        } catch (IndexOutOfBoundsException e) {
            return "I cannot mark a task that does not exist!";
        }
    }

    /**
     * Marks a task as not completed.
     *
     * @param i Index of task to be marked as not completed.
     * @return Associated message from Duke.
     */
    public String unmarkTask(int i) {
        try {
            Task t = this.tasks.get(i);
            return t.unmarkTask();
        } catch (IndexOutOfBoundsException e) {
            return "I cannot mark a task that does not exist!";
        }
    }

    /**
     * Prints a list of tasks containing the string.
     *
     * @param s String to find tasks with the matching string.
     * @return List of tasks with matching string.
     */
    public String find(String s) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            if (t.getDescription().contains(s)) {
                count++;
                sb.append(count + "." + t + "\n");
            }
        }
        return sb + "There are " + count + " tasks that contains: " + s;
    }
}
