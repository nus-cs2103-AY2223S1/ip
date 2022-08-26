package duke.main;

import java.util.Scanner;

import duke.errors.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Milk");
        System.out.println("What can I do for you?");
        sc = new Scanner(System.in);
    }

    /**
     * Reads user input
     * @return String of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the task list for user
     * @param tasks TaskList of tasks
     * @throws DukeException exception thrown in TaskList method
     */
    public void printList(TaskList tasks) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i + 1));
        }
    }

    /**
     * Prints out error received in loading
     */
    public void showLoadingError() {
        System.out.println("error in loading file!");
    };

    /**
     * Prints out error
     * @param error String in error message
     */
    public void showError(String error) {
        System.out.println(error);
    };

    /**
     * Prints successful task added message
     * @param task Task to be added
     * @param tasks TaskList to store task
     */
    public void addSuccess(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    };

    /**
     * Prints successful task deleted message
     * @param task Task to be added
     * @param tasks TaskList to store task
     */
    public void deleteSuccess(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    };

    /**
     * Prints exit message
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints task is marked message
     * @param task Task to be marked
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints task is not marked message
     * @param task Task to be unmarked
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints message required by program
     * @param message String that will be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    };
}
