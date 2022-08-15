package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String LINE = "--------------------------------------------------";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.printf("%s%n%s%n%s%n%s%n", LINE, "Hello! I'm Cortana", "What can I do for you?", LINE);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    public void showMark(String task) {
        System.out.printf("Nice! I've marked this task as done:%n%s%n", task);
    }

    public void showUnmark(String task) {
        System.out.printf("OK, I've marked this task as not done yet:%n%s%n", task);
    }

    public void showAdd(String task, int size) {
        System.out.printf("Got it. I've added this task:%n%s%nNow you have %d task%s in the list.%n",
                task, size, size > 1 ? "s" : "");
    }

    public void showDelete(String task, int size) {
        System.out.printf("Noted. I've removed this task:%n%s%nNow you have %d task%s in the list.%n",
                task, size, size != 1 ? "s" : "");
    }

    public void showInvalidCommandError() {
        System.out.println("I'm sorry, but I don't know what that means.");
    }

    public void showOutOfBoundsError() {
        System.out.println("The index provided is not within the list.");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}