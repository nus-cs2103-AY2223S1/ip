package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        if (sc.hasNext()) {
            return sc.nextLine();
        }
        return "";
    }

    public static void printEntryStatement() {
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/ ^  ^\\");
        System.out.println("Hello from Chan-bot!");
    }

    public static void printInitStatement() {
        System.out.println("What can I do for you?\n");
    }

    public static void printByeStatement() {
        System.out.println("Bye bye!");
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/    ^\\");
    }

    public static void printAddStatement(String res, int len) {
        System.out.println("Got it. I've added this task:\n" + "  " + res + "\nNow you have " + len
                + " tasks in the list.\n");
    }

    public static void printDeleteStatement(String res, int len) {
        System.out.println("Okay! The task: \n" + res + "\nhas been deleted forever.\n" +
                "You have " + len + " task" + ((len!=1)?"s ":" ") + "left!");
    }
}
