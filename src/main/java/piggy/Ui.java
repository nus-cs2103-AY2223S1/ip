package piggy;

import java.util.List;
import java.util.Scanner;

import piggy.task.Task;

class Ui {
    private final Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    void showWelcome() {
        System.out.println("Hello! I'm Piggy");
        System.out.println("What can I oink for you?");
        System.out.println("Use the following format for datetime: yyyy-MM-dd HH:mm");
    }

    void showBye() {
        System.out.println("Bye. Hope to oink you again soon!");
        sc.close();
    }

    void showTaskList(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        showTasks(tasks);
    }

    private void showTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    void showTaskAdded(Task task, int noRemainingTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + noRemainingTasks + " tasks in the list.");
    }

    void showTaskRemoved(Task task, int noRemainingTasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + noRemainingTasks + " tasks in the list.");
    }

    void showMarkAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

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

    void showDukeException(DukeException err) {
        System.out.println(err);
    }

    String readCommand() {
        return sc.nextLine();
    }
}
