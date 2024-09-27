package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public Ui() {}

    public static String showWelcome() {
        return "Hello, what can I do for you?";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns String representing the task marked.
     *
     * @param task Task to be marked.
     * @return String representing the task marked.
     */
    public String showTaskMarked(Task task) {
        assert task != null : "Marked task cannot be null";
        return "Nice! I've marked this task as done:\n\t" + task;
    }

    /**
     * Returns String representing the task unmarked.
     *
     * @param task Task to be unmarked.
     * @return String representing the task unmarked.
     */
    public String showTaskUnmarked(Task task) {
        assert task != null : "Unmarked task cannot be null";
        return "OK, I've marked this task as not done yet:\n\t" + task;
    }

    /**
     * Returns String representing the task added.
     *
     * @param task Task to be added.
     * @return String representing the task added.
     */
    public String showTaskAdded(Task task) {
        assert task != null : "Added task cannot be null";
        return "Got it. I've added this task:\n\t" + task;
    }

    /**
     * Returns String representing the task deleted.
     *
     * @param task Task to be deleted.
     * @return String representing the task deleted.
     */
    public String showTaskDeleted(Task task) {
        assert task != null : "Deleted task cannot be null";
        return "Noted. I've removed this task:\n\t" + task;
    }

    /**
     * Returns String representing the task tagged.
     *
     * @param task Task to be tagged.
     * @return String representing the task tagged.
     */
    public String showTagAdded(Task task) {
        return "Got it. I've tagged this task:\n\t" + task;
    }

    /**
     * Returns String representing the task untagged.
     *
     * @param task Task to be untagged.
     * @return String representing the task untagged.
     */
    public String showTagDeleted(Task task) {
        return "Got it. I've untagged this task:\n\t" + task;
    }

    /**
     * Returns String representing the number of tasks.
     *
     * @param count Number of tasks.
     * @return String representing the number of tasks.
     */
    public String showTaskCount(int count) {
        assert count >= 0 : "Task count should not be negative";
        if (count == 1) {
            return "Now you have 1 task in the list.";
        }
        return "Now you have " + (count) + " tasks in the list.";
    }

    /**
     * Returns String representing all tasks matching a keyword.
     *
     * @param tasks Tasklist with all matching tasks.
     * @return String representing the list of matching tasks.
     */
    public String showMatchingTasks(TaskList tasks) {
        if (tasks.getCount() == 0) {
            return "No results found!";
        }
        String result = "Matching results:";
        for (int i = 0; i < tasks.getCount(); i++) {
            result += "\n\t" + (i + 1) + "." + tasks.get(i);
        }
        return result;
    }
}
