package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList.
     *
     * @param tasks Array of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Returns the String representation of this TaskList.
     *
     * @return Array of String in according to the printing format.
     */
    public String list() {
        StringBuilder sb = new StringBuilder(tasks.size() + 1);
        sb.append("Here are the tasks in your list: \n");
        for (int i = 1; i < tasks.size() + 1; i++) {
            sb.append(tasks.get(i - 1).toStringWithIndex(i)).append("\n");
        }
        if (tasks.size() == 0) {
            return "No tasks found in the list!";
        }
        return sb.toString();
    }

    /**
     * Mark a specific task in this Tasklist.
     *
     * @param index Index of the Task to be marked.
     * @return Output message of a successful mark.
     */
    public String mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return "Nice! I've marked this task as done:\n" +
                "  " + task;
    }

    /**
     * Un-mark a specific task in this Tasklist.
     *
     * @param index Index of the Task to be unmarked.
     * @return Output message of a successful un-mark.
     */
    public String unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" +
                "  " + task;
    }

    /**
     * Deletes a specific task in this Tasklist.
     *
     * @param index Index of the Task to be deleted.
     * @return Output message of a successful delete.
     */
    public String delete(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        assert !this.tasks.contains(task);
        return "Noted. I've removed this task:\n" +
                "  " + task.toString() + "\n" +
                String.format("Now you have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Adds a new task to this TaskList.
     *
     * @param task Task to be added.
     * @return Output message after successfully adding the task.
     */
    public String add(Task task) {
        this.tasks.add(task);
        assert this.tasks.contains(task);
        return "Got it. I've added this task:\n" +
                "   " + task.toString() + "\n" +
                String.format("Now you have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Returns the String representation to be stored in the save file (tasks.txt).
     *
     * @return String formatted according to the save file.
     */
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toFileFormat()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Finds all matching tasks containing a specific string.
     *
     * @param toFind String to find.
     * @return Print format of all the tasks found.
     */
    public String find(String toFind) {
        StringBuilder sb = new StringBuilder().append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            assert curr != null;
            if (curr.description.contains(toFind)) {
                sb.append(curr.toStringWithIndex(i + 1)).append("\n");
            }
        }
        return sb.toString();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
