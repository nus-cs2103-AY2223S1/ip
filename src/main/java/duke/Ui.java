package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printGreeting() {
        System.out.println(LOGO);
        System.out.println(GREETING);
    }

    public String read() {
        return this.scanner.nextLine();
    }

    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void printList(String list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    public void printSizeOfList(int size) {
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void printMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        printTask(task);
    }

    public void printUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        printTask(task);
    }

    public void printDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
    }

    public void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printTask(Task task) {
        System.out.println(task);
    }
}
