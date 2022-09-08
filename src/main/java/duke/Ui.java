package duke;

/**
 * A UI to show outputs, success and error messages to the users.
 */
public class Ui {
    /**
     * Outputs all tasks.
     * @param tasks List of tasks.
     * @return String representing the list of tasks.
     */
    public String showTasks(TaskList tasks) throws DukeException {
        String content = "Here are the tasks in your list:\n";
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
        String content = String.format("Here are the scheduled tasks on %s in your list:\n", date);
        TaskList filteredTasks = tasks.getTasksByDate(date);
        filteredTasks.sort();
        for (int i = 1; i <= filteredTasks.size(); i++) {
            content += String.format("%d.%s\n", i, filteredTasks.get(i));
        }
        return content;
    }
}
