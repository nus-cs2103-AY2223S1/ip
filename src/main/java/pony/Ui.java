package pony;

import pony.task.Task;

import java.util.Scanner;

/**
 * A UI class handling all user interaction.
 */
public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    };

    public void printWelcome() {
        String message = "Hello! I'm pony.Pony" + "\n" + "What can I do for you?";
        System.out.println(message);
    }

    public void printLine() {
        String message = "==============================================";
        System.out.println(message);
    }
    public String readCommand() {
        return this.sc.nextLine();
    }

    public void printExit() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    public void printMarkedTask(Task task) {
        String message = "Nice! I've marked this task as done:\n";
        message += task.toString();
        System.out.println(message);
    }

    public void printUnmarkedTask(Task task) {
        String message = "OK, I've marked this task as not done yet:\n";
        message += task.toString();
        System.out.println(message);
    }

    public void printDeletedTask(Task task, TaskList tasks) {
        String message = "Noted. I've removed this task:\n";
        message += task.toString() + "\n" + "Now you have " + tasks.getTasksCount() + " tasks in the list.";
        System.out.println(message);
    }

    public void printAddedTask(Task task, TaskList tasks) {
        String message = "Got it. I've added this task: ";
        message += task.toString() + "\n" + "Now you have " + tasks.getTasksCount() + " tasks in the list.";
        System.out.println(message);
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.sizeOf() == 0) {
            System.out.println("Nothing on the list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.sizeOf(); i++) {
                int serialNumber = i + 1;
                System.out.println(serialNumber + ". " + tasks.getTask(i).toString());
            }
        }

    }
}
