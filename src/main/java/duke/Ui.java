package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Edric\nWhat can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLine() {
        System.out.println("__________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error Loading Storage File!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Got it. I've removed this task:");
        System.out.println("\t " + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.format("%d. %s\n", i + 1, curr.toString());
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

}
