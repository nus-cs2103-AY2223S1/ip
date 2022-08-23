package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError(String message) {
        System.out.println("Error loading tasks from storage: " + message + "\n");
    }

    public void showAddTask(Task newTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + "." + tasks.getTask(i));
        }
    }

    public void showMarkTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
    }

    public void showUnmarkTask(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showDeleteTask(Task deletedTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void emptyLine() {
        System.out.println();
    }

    public void showFindTasks(TaskList tasks, List<Integer> matches) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i : matches) {
            System.out.println(i + "." + tasks.getTask(i));
        }
    }
}
