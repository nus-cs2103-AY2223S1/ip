package duke;

import java.util.List;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner in;

    Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    private String prettyPrint(String text) {
        String[] lines = text.split("\n");
        StringBuilder temp = new StringBuilder("    ____________________________________________________________\n");
        for (String line : lines) {
            temp.append("     ");
            temp.append(line);
            temp.append("\n");
        }
        temp.append("    ____________________________________________________________\n");
        return temp.toString();
    }

    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
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
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < result.size(); i++) {
            temp.append(i + 1);
            temp.append(".");
            temp.append(result.get(i));
            temp.append("\n");
        }
        return temp.toString();
    }
}
