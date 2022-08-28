package duke;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_BAR = "-".repeat(100);
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_BAR);
    }

    public void greetings() {
        String message = "Hello! I'm Duke\n" + "What can I do for you?";
        printMessage(message);
    }

    public void printBye() {
        String message = "Bye. Hope to see you again soon!";
    }

    public void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public String listAllItems(List<Task> tasks) {
        //printLine();
        String result = "";
        result += "-".repeat(100);
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            result += "\n" + number + ". " + tasks.get(i).toString();
            //System.out.println(number + ". " + tasks.get(i).toString());
        }
        result += "\n" + "-".repeat(100);
        return result;
    }
}
