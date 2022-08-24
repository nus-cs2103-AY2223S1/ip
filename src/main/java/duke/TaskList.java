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
     */
    public void addTodo(String s) {
        Todo t = new Todo(s);
        tasks.add(t);
        System.out.println("Successfully added: " + t);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    /**
     * Adds a task with deadline to the list.
     *
     * @param s Task description.
     * @param d Deadline in LocalDate format.
     */
    public void addDeadline(String s, LocalDate d) {
        Deadline deadline = new Deadline(s, d);
        tasks.add(deadline);
        System.out.println("Successfully added: " + deadline);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    /**
     * Adds a task with deadline to the list.
     *
     * @param s Task description.
     * @param d Deadline in String format.
     */
    public void addDeadline(String s, String d) {
        Deadline deadline = new Deadline(s, d);
        tasks.add(deadline);
        System.out.println("Successfully added: " + deadline);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    /**
     * Adds an event to the list.
     *
     * @param s Event description.
     * @param time Event time in LocalDate format.
     */
    public void addEvent(String s, LocalDate time) {
        Event event = new Event(s, time);
        tasks.add(event);
        System.out.println("Successfully added: " + event);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    /**
     * Adds an event to the list.
     *
     * @param s Event description.
     * @param time Event time in String format.
     */
    public void addEvent(String s, String time) {
        Event event = new Event(s, time);
        tasks.add(event);
        System.out.println("Successfully added: " + event);
        System.out.println("You have " + tasks.size() + " tasks in the list now");
    }

    /**
     * Prints the list of tasks.
     */
    public void getList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("There are " + tasks.size() + " tasks in the list.");
    }

    /**
     * Delete a task from the list.
     *
     * @param index Index of the task in the list.
     */
    public void deleteTask(int index) {
        try {
            if (index >= tasks.size() || index < 0) {
                System.out.println("I cannot delete a task that does not exist!");
            } else {
                Task t = tasks.get(index);
                tasks.remove(t);
                System.out.println("Successfully deleted: " + t);
                System.out.println("You have " + tasks.size() + " tasks in the list now");
            }
        } catch (NumberFormatException e) {
            System.out.println("I cannot delete a task that does not exist!");
        }
    }

    /**
     * Marks a task as completed.
     *
     * @param i Index of task to be marked as completed.
     */
    public void markTask(int i) {
        try {
            Task t = this.tasks.get(i);
            t.markTask();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("I cannot mark a task that does not exist!");
        }
    }

    /**
     * Marks a task as not completed.
     *
     * @param i Index of task to be marked as not completed.
     */
    public void unmarkTask(int i) {
        try {
            Task t = this.tasks.get(i);
            t.unmarkTask();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("I cannot mark a task that does not exist!");
        }
    }
}
