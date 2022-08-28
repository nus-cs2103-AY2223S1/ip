package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     * @param index the index of task to delete
     */
    public void delete(int index) {
        try {
            Task t = tasks.remove(index - 1);
            int length = tasks.size();
            String output = length == 1 ? " task in the list." : " tasks in the list.";
            System.out.println("Got it. I've deleted this task:\n" + t
                    + "\nNow you have " + length + output);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
    }

    /**
     * Marks a task as done.
     * @param index the index of task to mark as done
     */
    public void mark(int index) {
        try {
            Task t = tasks.get(index - 1);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + t);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
    }

    /**
     * Marks a task as not done.
     * @param index the index of task to mark as not done
     */
    public void unmark(int index) {
        try {
            Task t = tasks.get(index - 1);
            t.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(i++ + ". " + t.toString() + "\n");
        }
        return sb.toString();
    }
}
