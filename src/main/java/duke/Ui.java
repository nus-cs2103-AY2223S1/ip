package duke;

import java.util.Scanner;

/**
 * The Ui class handles the output to be printed by Bob that is shown to the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Returns the initial welcome message to be shown to the user.
     */
    public static String printWelcomeMessage() {
        return "Hey there! I'm Bob\nWhat can I do for you?";
    }

    /**
     * Returns the bye message to the user.
     */
    public String printByeMessage() {
        sc.close();
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Returns the mark message as well as the task that has been marked.
     *
     * @param task The task that has been marked.
     */
    public String printMark(String task) {
        return String.format("Nice! I have marked this Bob task as done: %n%s%n", task);
    }

    /**
     * Returns the message as well the task that has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public String printUnmark(String task) {
        return String.format("OK, I've marked this Bob task as not done yet: %n%s%n", task);
    }

    /**
     * Returns the message that task has been added.
     *
     * @param addedTask String representation of added task.
     * @param size Size of list after task is added.
     */
    public String printAdd(String addedTask, int size) {
        return String.format("Got it. I've added this Bob task: %n%s%nNow you have %d task%s in the list.",
                addedTask, size, size > 1 ? "s" : "");
    }

    /**
     * Returns message of task being deleted.
     *
     * @param deletedTask String representation of deleted task.
     * @param size The size of list after task is deleted.
     */
    public String printDelete(String deletedTask, int size) {
        return String.format("Noted. I've removed this Bob task: %n%s%nNow you have %d task%s in the list.",
                deletedTask, size, size > 1 ? "s" : "");
    }

    /**
     * Returns loading error if file does not exist.
     */
    public String printLoadingError() {
        return "No saved data found";
    }

    public String printUpdate(String updatedTask) {
        return String.format("Ok , I have updated this task: %n%s", updatedTask);
    }

    /**
     * Returns any given message.
     *
     * @param message Given message.
     */
    public String printAnyOtherMessage(String message) {
        return message;
    }
}
