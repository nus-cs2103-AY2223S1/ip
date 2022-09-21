package duke.oop;

import duke.task.Task;

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

    public String addTwoLines(String message) {
        return HORIZONTAL_BAR + "\n" + message + "\n" + HORIZONTAL_BAR;
    }

    public String listAllItems(List<Task> tasks) {
        if(tasks.size() == 0) {
            return "There is no task right now!";
        }
        String result = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            int number = i + 1;
            result += number + ". " + tasks.get(i).toString() + "\n";
        }
        int size = tasks.size();
        result += size + ". " + tasks.get(size - 1).toString();
        return result;
    }
}
