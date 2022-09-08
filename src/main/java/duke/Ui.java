package duke;

/**
 * A UI to show outputs, success and error messages to the users.
 */
public class Ui {
    /**
     * Outputs all tasks or all tasks on a certain date.
     * @param tasks List of tasks.
     * @param date Optional date to find tasks from.
     * @return String representing the list of tasks.
     */
    public String showTasks(TaskList tasks, String... date) throws DukeException {
        String content = "Here are the tasks in your list:\n";
        if (date.length == 0) {
            for (int i = 1; i <= tasks.size(); i++) {
                content += String.format("%d.%s\n", i, tasks.get(i));
            }
            return content;
        }
        return showTasks(tasks.getTasksByDate(date[0]));
    }
}
