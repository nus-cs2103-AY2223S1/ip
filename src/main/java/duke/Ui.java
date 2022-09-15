package duke;

import java.util.List;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    Ui() {
    }

    public static String showTasks(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    public static String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public static String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public static String showAdded(Task task, int num) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + num + " tasks in the list.";
    }

    public static String showRemoved(Task task, int num) {
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + num + " tasks in the list.";
    }

    public static String showError(String message) {
        return ":( OOPS!!! " + message;
    }

    public static String showResults(List<Task> tasks) {
        return "Here are your search results:\n" + listTasks(tasks);
    }

    /**
     * Formats list of tasks.
     *
     * @param tasks Task list.
     * @return Formatted task list.
     */
    public static String listTasks(List<Task> tasks) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            temp.append(i + 1);
            temp.append(".");
            temp.append(tasks.get(i));
            temp.append("\n");
        }
        return temp.toString();
    }
}
