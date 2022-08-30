package duke.main;

import java.util.ArrayList;


import duke.errors.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    private StringBuilder sc = new StringBuilder();
    // redundant
    public String getOutput() {
        return sc.toString();
    }

    public void resetOutput() {
        sc.setLength(0);
    }

    /**
     * Prints the task list for user
     * @param tasks TaskList of tasks
     * @throws DukeException exception thrown in TaskList method
     */
    public void printList(TaskList tasks) throws DukeException {
        System.out.println("Here are the tasks in your list:");
        sc.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sc.append(i + 1 + "." + tasks.get(i + 1) + "\n");
            System.out.println(i + 1 + "." + tasks.get(i + 1));
        }
    }

    /**
     * Prints out error received in loading
     */
    public void showLoadingError() {
        System.out.println("error in loading file!");
        sc.append("error in loading file!");
    };

    /**
     * Prints out error
     * @param error String in error message
     */
    public void showError(String error) {
        System.out.println(error);
        sc.append(error);
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
        sc.append("Got it. I've added this task:\n");
        sc.append(task + "\n");
        sc.append("Now you have " + tasks.size() + " tasks in the list.");
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
        sc.append("Noted. I've removed this task:\n");
        sc.append(task + "\n");
        sc.append("Now you have " + tasks.size() + " tasks in the list.");
    };

    /**
     * Prints exit message
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.append("Bye. Hope to see you again soon!");
    }

    /**
     * Prints task is marked message
     * @param task Task to be marked
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        sc.append("Nice! I've marked this task as done:\n");
        sc.append(task);
    }

    /**
     * Prints task is not marked message
     * @param task Task to be unmarked
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        sc.append("OK, I've marked this task as not done yet:\n");
        sc.append(task);
    }

    /**
     * Prints message required by program
     * @param message String that will be printed
     */
    public void printMessage(String message) {
        System.out.println(message);
    };

    /**
     * Prints find message
     * @param matchingTasks Arraylist of tasks matching keyword
     */
    public void printFind(ArrayList<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        sc.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
            sc.append((i + 1) + "." + matchingTasks.get(i).toString() + "\n");
        }
    }
}
