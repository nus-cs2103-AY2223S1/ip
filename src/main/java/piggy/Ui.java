package piggy;

import java.util.List;
import java.util.Scanner;

import piggy.task.Task;

/**
 * Manages the user-interaction UI.
 */
class Ui {
    private final Scanner sc;

    /**
     * Creates a new UI object. Uses STDIN for input and STDOUT for output.
     */
    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows the welcome message on the output.
     */
    void showWelcome() {
        System.out.println("Hello! I'm Piggy");
        System.out.println("What can I oink for you?");
        System.out.println("Use the following format for datetime: yyyy-MM-dd HH:mm");
    }

    /**
     * Shows the bye message on the output and stops receiving user input.
     */
    void showBye() {
        System.out.println("Bye. Hope to oink you again soon!");
        sc.close();
    }

    /**
     * Shows the list of tasks on the output.
     *
     * @param tasks The list of tasks to show.
     */
    void showTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        showTasks(tasks);
    }

    private void showTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Show that a task has been added along with the remaining tasks.
     *
     * @param task             The added task to show.
     * @param noRemainingTasks The number of remaining tasks.
     */
    void showTaskAdded(Task task, int noRemainingTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + noRemainingTasks + " tasks in the list.");
    }

    /**
     * Show that a task has been removed along with the remaining tasks.
     *
     * @param task             The removed task to show.
     * @param noRemainingTasks The number of remaining tasks.
     */
    void showTaskRemoved(Task task, int noRemainingTasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + noRemainingTasks + " tasks in the list.");
    }

    /**
     * Show that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    void showMarkAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Show that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    void showMarkAsNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Show the list of tasks found.
     *
     * @param tasks The list of tasks.
     */
    void showTasksFound(List<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("No matching tasks found in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            showTasks(tasks);
        }
    }

    /**
     * Shows a DukeException on the output.
     *
     * @param err The exception to show.
     */
    void showDukeException(DukeException err) {
        System.out.println(err);
    }

    /**
     * Reads a command from the input.
     *
     * @return The command from the input.
     */
    String readCommand() {
        return sc.nextLine();
    }
}
