package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new task list with the given tasks.
     *
     * @param tasks the tasks to be added to the task list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index the index of task to delete
     */
    public Task delete(int index) {
        try {
            Task t = tasks.remove(index - 1);
            assert t != null : "Task should not be null";
            int length = tasks.size();
            String output = length == 1 ? " task in the list." : " tasks in the list.";
            System.out.println("Got it. I've deleted this task:\n" + t
                    + "\nNow you have " + length + output);
            return t;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
        return null;
    }

    /**
     * Marks a task as done.
     *
     * @param index the index of task to mark as done
     */
    public String mark(int index) {
        try {
            Task t = tasks.get(index - 1);
            assert t != null : "Task should not be null";
            t.markAsDone();
            return "Nice! I've marked this task as done:\n" + t;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
        return "☹ OOPS!!! The index is out of bounds.";
    }

    /**
     * Marks a task as not done.
     *
     * @param index the index of task to mark as not done
     */
    public String unmark(int index) {
        try {
            Task t = tasks.get(index - 1);
            assert t != null : "Task should not be null";
            t.markAsNotDone();
            return "OK, I've marked this task as not done yet:\n" + t;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
        return "☹ OOPS!!! The index is out of bounds.";
    }

    /**
     * Changes the priority of a task at the given index.
     *
     * @param priority the priority of the task
     * @param index    the index of the task
     * @return the message to be printed
     */
    public String setPriority(String priority, int index) {
        try {
            Task t = tasks.get(index - 1);
            assert t != null : "Task should not be null";
            t.priority(priority);
            return "Nice! I've assigned this task priority "
                    + priority
                    + ":\n"
                    + t;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The index is out of bounds.");
        }
        return "☹ OOPS!!! The index is out of bounds.";
    }

    /**
     * Finds all tasks that contain the given keyword.
     *
     * @param searchTerm the keyword to search for
     */
    public TaskList find(String searchTerm) {
        return tasks.stream()
                .filter((t) -> t.getDescription().contains(searchTerm))
                .collect(TaskList::new, TaskList::add, TaskList::addAll);
    }

    /**
     * Adds all tasks from the given task list to this task list.
     *
     * @param other the task list to add from
     */
    public void addAll(TaskList other) {
        tasks.addAll(other.tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns string representation of the task list.
     *
     * @return string representation of the task list
     */
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
