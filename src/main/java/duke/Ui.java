package duke;

import java.util.Scanner;

public class Ui {

    private static final String LINE = "__________________________________";
    private static final String MESSAGE_WELCOME = "Hi there! Baymax at your service. Let me retrieve your stored task list!";
    private static final String MESSAGE_BYE = "Goodbye!";
    private static final Scanner sc = new Scanner(System.in);


    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printWelcome() {
        printLine();
        System.out.println(MESSAGE_WELCOME);
    }

    public static void printBye() {
        System.out.println(MESSAGE_BYE);
    }

    public static void printSuccessfulLoad() {
        System.out.println("Found it!");
        printLine();
    }

    public static void printFailedLoad() {
        System.out.println("Seems like you don't have a task list. I've initialized one for you!");
        printLine();
    }

    public static void printNumberOfTasks(int size) {
        if (size < 2) {
            System.out.println("Now you have " + size + " task in the list.");
        } else {
            System.out.println("Now you have " + size + " tasks in the list.");
        }
    }

    public static void printAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:\n " + task);
        printNumberOfTasks(size);
    }

    public static void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n  " + task);
        printNumberOfTasks(size);
    }

    public static void printTaskMarkedDone(Task task, int index) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public static void printTaskMarkedUndone(Task task, int index) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public static void printError(String error) {
        System.out.println(error);
    }

    public static String readCommand() {
        System.out.println("Please enter a command: ");
        return sc.nextLine();
    }

}
