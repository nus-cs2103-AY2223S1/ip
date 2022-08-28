package duke;

import java.util.Scanner;

public class Ui {
    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showLoadingError() {
        System.err.println("☹ OOPS!!! I'm sorry, but I can't load your tasks :-(");
    }

    public void showSavingError() {
        System.err.println("☹ OOPS!!! I'm sorry, but I can't save your tasks :-(");
    }

    public void showNoTasks() {
        System.out.println("You have no tasks.\n" + "What can I do for you?");
    }

    public void showAddTask(Task task, int size) {
        String output = size == 1 ? " task in the list." : " tasks in the list.";
        System.out.println("Got it. I've added this task:\n" + task
                + "\nNow you have " + size + output);
    }

    public void showDeleteTask(Task task, int size) {
        String output = size == 1 ? " task in the list." : " tasks in the list.";
        System.out.println("Got it. I've deleted this task:\n" + task
                + "\nNow you have " + size + output);
    }

    public void showList(TaskList tasks) {
        System.out.print(tasks);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showInvalidCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }
}
