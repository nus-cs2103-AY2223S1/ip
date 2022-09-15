package duke;

/**
 * A UI to show outputs, success and error messages to the users.
 */
public class Ui {
    public static final String DEADLINE = "Got it. I've added this deadline:\n  %s\nNow you have %d tasks in the list.";
    public static final String DELETE = "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.";
    public static final String EVENT = "Got it. I've added this event:\n  %s\nNow you have %d tasks in the list.";
    public static final String LIST = "Here are the tasks in your list:\n";
    public static final String MARK = "Nice! I've marked this task as done:\n  %s";
    public static final String SAVE_ERROR = "%s.\nBye. Hope to see you again soon!";
    public static final String SAVE_SUCCESS = "Data is saved successfully.\nBye. Hope to see you again soon!";
    public static final String SCHEDULE = "Here are the scheduled tasks on %s in your list:\n";
    public static final String TODO = "Got it. I've added this todo:\n  %s\nNow you have %d tasks in the list.";
    public static final String UNMARK = "OK, I've marked this task as not done yet:\n  %s";

    /**
     * Outputs all tasks.
     * @param tasks List of tasks.
     * @return String representing the list of tasks.
     */
    public String showTasks(TaskList tasks) throws DukeException {
        String content = LIST;
        for (int i = 1; i <= tasks.size(); i++) {
            content += String.format("%d.%s\n", i, tasks.get(i));
        }
        return content;
    }

    /**
     * Outputs all tasks on a certain date.
     * @param tasks List of tasks.
     * @param date Optional date to find tasks from.
     * @return String representing the list of tasks.
     */
    public String viewScheduleOnDate(TaskList tasks, String date) throws DukeException {
        String content = String.format(SCHEDULE, date);
        TaskList filteredTasks = tasks.getTasksByDate(date);
        filteredTasks.sort();
        for (int i = 1; i <= filteredTasks.size(); i++) {
            content += String.format("%d.%s\n", i, filteredTasks.get(i));
        }
        return content;
    }

    public String showSuccess(String message, Object... args) {
        return String.format(message, args);
    }
}
