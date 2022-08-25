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

    void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        printMessage(exit);
    }

    void printMessage(String input) {
        linePrint();
        System.out.println('\t' + input);
        linePrint();
    }

    void linePrint() {
        System.out.println("\t____________________________________________________________");
    }

    void showLoadingError() {
        printMessage("An error occurred when loading the file :(");
    }

    void showError(String message) {
        printMessage(message);
    }

    String readCommand() {
        return sc.nextLine();
    }

    void listTasks(ArrayList<Task> tasks) {
        linePrint();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        linePrint();
    }
}
