package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    private final static String LINE = "____________________________________________________________";
    private final static String INDENTATION = "   ";
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads user input.
     * @return user input as string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Prints exit message.
     */
    public void showGoodByeMessage() {
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints out all tasks.
     * @param tasks TaskList containing tasks list.
     */
    public void showListMessage(TaskList tasks) {
        int numOfTasks = tasks.getSize();
        if (numOfTasks == 0) {
            System.out.println(INDENTATION + "You do not have any tasks in your list right now.");
        } else {
            System.out.printf(INDENTATION + "Here %s the task%s in your list:\n",
                    numOfTasks > 1 ? "are" : "is", numOfTasks > 1 ? "s" : "");
            for (int i = 1; i <= numOfTasks; i++) {
                System.out.println(INDENTATION + String.valueOf(i) + ". " +
                        tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Prints out tasks that contain search keyword.
     * @param tasks TaskList with tasks containing search keyword.
     */
    public void showFindMessage(TaskList tasks) {
        int numOfTasks = tasks.getSize();
        if (numOfTasks == 0) {
            System.out.println(INDENTATION + "There are no matching tasks in your list.");
        } else {
            System.out.printf(INDENTATION + "Here %s the task%s in your list:\n",
                    numOfTasks > 1 ? "are" : "is", numOfTasks > 1 ? "s" : "");
            for (int i = 1; i <= numOfTasks; i++) {
                System.out.println(INDENTATION + String.valueOf(i) + ". " +
                        tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Prints out task that has been marked as done.
     * @param task task to be marked as done.
     */
    public void showMarkMessage(Task task) {
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + task.toString());
    }

    /**
     * Prints out task that has been unmarked as done.
     * @param task task to be unmarked as done.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + INDENTATION + task.toString());
    }

    /**
     * Prints out task that is added.
     * @param tasks TaskList containing tasks list.
     * @param task task that is added.
     */
    public void showAddTaskMessage(TaskList tasks, Task task) {
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + "added: " + task.toString());
        System.out.println(INDENTATION +
                "Now you have " + String.valueOf(tasks.getSize()) + " tasks in the list.");
    }

    /**
     * Prints out task that is deleted.
     * @param tasks TaskList containing tasks list.
     * @param task task that is deleted.
     */
    public void showDeleteMessage(TaskList tasks, Task task) {
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + INDENTATION + task.toString());
        System.out.println(INDENTATION +
                "Now you have " + String.valueOf(tasks.getSize()) + " tasks in the list.");
    }

    /**
     * Prints out error message.
     * @param message error message.
     */
    public void showError(String message) {
        System.out.println(INDENTATION + message);
    }

    /**
     * Prints out loading error message.
     */
    public void showLoadingError() {
        System.out.println(INDENTATION + "There was no saved data found.");
    }

    /**
     * Prints out out of bounds error message.
     */
    public void showOutOfBoundsError() {
        System.out.println(INDENTATION + "Please choose a index provided within the list of tasks");
    }

    /**
     * Prints out illegal argument error.
     */
    public void showIllegalArgumentError() {
        System.out.println(INDENTATION + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
