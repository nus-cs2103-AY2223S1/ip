package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String horizontalLine = "_____________________________________________________________";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printHorizontalLine() {
        System.out.println(horizontalLine);
    }

    public void printWelcomeMessage() {
        System.out.printf("Hey there! I'm Bob\nWhat can I do for you?\n");
        printHorizontalLine();
    }

    public void printByeMessage() {
        System.out.println("Bye! Hope to see you again soon!");
        sc.close();
    }

    public void printMark(String task) {
        System.out.println("Nice! I have marked this bob task as done:");
        System.out.println(task);
    }

    public void printUnmark(String task) {
        System.out.println("OK, I've marked this bob task as not done yet:");
        System.out.println(task);
    }

    public void printAdd(String add, int size) {
        System.out.println("Got it. I've added this bob task:");
        System.out.println(add);
        System.out.println(String.format("Now you have %d bob task%s in the list.", size, size != 1 ? "s" : ""));
    }

    public void printDelete(String delete, int size) {
        System.out.println("Noted. I've removed this bob task:");
        System.out.println(delete);
        System.out.println(String.format("Now you have %d bob task%s in the list.", size, size != 1 ? "s" : ""));
    }

    public void printLoadingError() {
        System.out.println("No saved data found");
    }

    public void printAnyOtherMessage(String message) {
        System.out.println(message);
    }




}
