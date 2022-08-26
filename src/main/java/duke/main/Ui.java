package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    void greetingMessage() {
        String greeting = "Hello! I'm Duke\n\t" +
                "What can I do for you?";
        printMessage(greeting);
    }

    public void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        printMessage(exit);
    }

    public void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    public void linePrint() {
        System.out.println("\t____________________________________________________________");
    }

    public void showLoadingError() {
        printMessage("An error occurred when loading the file :(");
    }

    public void showError(String message) {
        printMessage(message);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void listTasks(ArrayList<Task> tasks) {
        linePrint();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        linePrint();
    }

    public void listFoundTasks(ArrayList<Task> tasks) {
        linePrint();
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        linePrint();
    }
}
