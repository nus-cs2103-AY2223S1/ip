package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        showToUser(" Hello! I'm Duke\n"
                + " What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showToUser(String s) {
        System.out.println(s);
    }
    /**
     * Show divider line.
     *
     */
    public void showLine() {
        showToUser("____________________________________________________________");
    }

    public void showRemovingTaskMessage(Task t, int size) {
        showToUser("\nNoted. I've removed this task:\n  "
                + t.toString()
                + "\n Now you have " + size + " tasks in the list\n");
    }

    public void showAddingTaskMessage(Task t, int size) {
        showToUser(" Got it. I've added this task:\n  "
                + t.toString()
                + "\n Now you have " + size + " tasks in the list");
    }

    public void showLoadingError(DukeException e) {
        showToUser(e.getMessage());
    }

    public void showMarkTaskMessage(Task t) {
        showToUser(" Nice! I've marked this task as done:\n"
                + "  [X] "
                + t.getDescription());
    }

    public void showUnmarkTaskMessage(Task t) {
        showToUser(" OK, I've marked this task as not done yet:\n"
                + "  [ ] "
                + t.getDescription());
    }

    public void showListMessage(String s) {
        showToUser(" Here are the tasks in your list:" + s);
    }

    public void showExitMessage() {
        showToUser(" Bye ! hope to see you soon.");
    }

    public void showFoundTask(String s) {
        showToUser(" Here are the matching tasks in your list:" + s);
    }
}
