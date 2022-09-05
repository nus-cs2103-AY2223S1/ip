package duke;

import java.util.List;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    Ui() {
    }

    public String showTasks(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }

    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public String showAdded(Task task, int num) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + num + " tasks in the list.";
    }

    public String showRemoved(Task task, int num) {
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + num + " tasks in the list.";
    }

    public String showError(String message) {
        return ":( OOPS!!! " + message;
    }

    /**
     * Formats search results.
     *
     * @param result Task list from search result.
     * @return Formatted search results.
     */
    public String showResults(List<Task> result) {
        return result.toString();
    }
}
