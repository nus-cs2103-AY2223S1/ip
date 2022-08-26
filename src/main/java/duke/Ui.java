package duke;

import java.util.Scanner;
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void helloMessage() {
        System.out.println("Hello! I'm SmartBot\nWhat can I do for you?");
    }

    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showLoadingError() {
        System.out.println("Loading error...");
    }
}
