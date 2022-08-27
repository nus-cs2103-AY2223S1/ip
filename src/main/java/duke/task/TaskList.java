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
     * @param tasks an Array of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    /**
     * Returns the String representation of this TaskList
     *
     * @return an Array of String in according to the printing format.
     */
    public String[] toPrintFormat() {
        String[] lines = new String[tasks.size() + 1];
        lines[0] = "Here are the tasks in your list: ";
        for (int i = 1; i < tasks.size() + 1; i++) {
            lines[i] = tasks.get(i - 1).toStringWithIndex(i);
        }
        if (lines.length == 1) {
            lines[0] = "No tasks found in the list!";
        }
        return lines;
    }

    /**
     * Mark a specific task in this Tasklist.
     *
     * @param index the index of the Task to be marked.
     * @return the output message of a successful mark.
     */
    public String[] mark(int index) {
        Task task = this.tasks.get(index);
        task.mark();
        return new String[] {"Nice! I've marked this task as done:",
                "  " + task.toString()};
    }

    /**
     * Unmark a specific task in this Tasklist.
     *
     * @param index the index of the Task to be unmarked.
     * @return the output message of a successful unmark.
     */
    public String[] unmark(int index) {
        Task task = this.tasks.get(index);
        task.unmark();
        return new String[] {"OK, I've marked this task as not done yet:",
                "  " + task.toString()};
    }

    /**
     * Deletes a specific task in this Tasklist.
     *
     * @param index the index of the Task to be deleted.
     * @return the output message of a successful delete.
     */
    public String[] delete(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return new String[] {"Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", this.tasks.size())};
    }

    /**
     * Adds a new task to this TaskList.
     *
     * @param task the task to be added
     * @return the output message after successfully adding the task.
     */
    public String[] add(Task task) {
        this.tasks.add(task);
        return new String[] {"Got it. I've added this task:",
                "   " + task.toString(),
                String.format("Now you have %d tasks in the list.", this.tasks.size())};
    }

    /**
     * Returns the String representation to be stored in the save file (tasks.txt)
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
}
