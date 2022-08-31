package duke;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "\n========================================================";
    private static final String WELCOME_MESSAGE = "Hello, my name is Duke!\nHow can I help you today?";
    private static final String EXIT_MESSAGE = LINE + "\nGoodbye! Looking forward to see you again soon!" + LINE;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String readInput() {
        return input.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println(LOGO + LINE + "\n" + WELCOME_MESSAGE + LINE);
    }

    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public void printLoadingError(String fileName) {
        System.out.println("Failed to find the task file: " + fileName + "\nCreating a new task file...");
    }

    public void printLoadingSuccessMessage() {
        System.out.println("Previously saved tasks have been loaded!");
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printUnknownCommandMessage() {
        printLine();
        System.out.print("I'm sorry, but I don't know what that means");
        printLine();
    }

    public void printError(String message) {
        printLine();
        System.out.print(message);
        printLine();
    }

    public void printEmptyListMessage() {
        System.out.println(Ui.LINE + "\nYour list is empty! Why not add a task to it first?" + Ui.LINE);
    }

    public void printTaskMarked(Task t) {
        printLine();
        System.out.println("\nOkay, I have marked this task as done: \n" + t.toString());
        printLine();
    }

    public void printTaskUnmarked(Task t) {
        printLine();
        System.out.println("\nOkay, I have marked this task as not done: \n" + t.toString());
        printLine();
    }

    public void printToDoCreated(Task t, int s) {
        printLine();
        System.out.println("\nGot it! I have added this task to your list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }

    public void printDeadlineCreated(Task t, int s) {
        printLine();
        System.out.println("\nGot it! I have added this task to your list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }

    public void printEventCreated(Task t, int s) {
        printLine();
        System.out.println("\nGot it! I have added this task to your list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }

    public void printTaskDeleted(Task t, int s) {
        printLine();
        System.out.println("\nOkay, I have removed this task from the list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }
}