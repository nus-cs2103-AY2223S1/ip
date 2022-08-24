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

    private void prettyPrint(String text) {
        String[] lines = text.split("\n");
        StringBuilder temp = new StringBuilder("    ____________________________________________________________\n");
        for (String line : lines) {
            temp.append("     ");
            temp.append(line);
            temp.append("\n");
        }
        temp.append("    ____________________________________________________________\n");
        System.out.println(temp);
    }

    public void showWelcome() {
        prettyPrint("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showBye() {
        prettyPrint("Bye. Hope to see you again soon!");
    }

    public void showTasks(TaskList tasks) {
        prettyPrint("Here are the tasks in your list:\n" + tasks.toString());
    }

    public void showMarked(Task task) {
        prettyPrint("Nice! I've marked this task as done:\n  " + task);
    }

    public void showUnmarked(Task task) {
        prettyPrint("OK, I've marked this task as not done yet:\n  " + task);
    }

    public void showAdded(Task task, int num) {
        prettyPrint("Got it. I've added this task:\n  " + task + "\nNow you have " + num + " tasks in the list.");
    }

    public void showRemoved(Task task, int num) {
        prettyPrint("Noted. I've removed this task:\n  " + task + "\nNow you have " + num + " tasks in the list.");
    }

    public void showError(String message) {
        prettyPrint("☹ OOPS!!! " + message);
    }

    /**
     * Shows search results.
     *
     * @param result Task list from search result.
     */
    public void showResults(List<Task> result) {
        StringBuilder temp = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < result.size(); i++) {
            temp.append(i + 1);
            temp.append(".");
            temp.append(result.get(i));
            temp.append("\n");
        }
        prettyPrint(temp.toString());
    }
}
