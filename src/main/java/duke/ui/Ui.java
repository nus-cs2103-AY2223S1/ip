package duke.ui;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public void printWelcome() {
        System.out.println("Hello! I'm Duke" + "\nHow can I help you?");
    }

    public void printGoodbye() {
        System.out.println("Bye! See you again :)");
    }

    public void printMark(Task task) {
        System.out.println("Great! This task is completed:\n" + task);
    }

    public void printUnmark(Task task) {
        System.out.println("Okay, this task is now unchecked:\n" + task);
    }

    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    public void printDelete(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }
}
