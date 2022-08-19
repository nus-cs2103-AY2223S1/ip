package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String line = "_______________________________";


    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Handles what to show during Duke's startup.
     */
    public void showWelcome() {
        showLine();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(line);
    }


    public void showLoadingError() {
        String temp = line + "Storage file not found at specified path!" + line;
        System.out.println(temp);
    }


    public void showAddedTask(Task itemToAdd, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(itemToAdd);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list");
    }

    public void showDeletedTask(Task itemToRemove, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(itemToRemove);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list");
    }

    public void showMarkedTask(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markedTask);
    }

    public void showUnmarkedTask(Task unmarkedTask) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
    }
}
