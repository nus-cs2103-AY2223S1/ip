package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user interface class that
 * deals with interactions with the user.
 *
 * @author Leong Jia Hao Daniel
 */
public class Ui {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String greeting = "Hello, I'm\n" + logo + "How may I help you today?";

    private static String farewell = "Goodbye! Hope to see you again!";


    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showWelcome() {
        showLine();
        formatMessage(greeting);
        showLine();
    }

    public void formatMessage(String message) {
        System.out.println("Duke says:");
        System.out.println(message);
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("Error loading the file!");
    }

    public void displayList(ArrayList<Task> arrayList) {
        int i = 1;
        String display = "Here are the tasks in your list: ";
        for (Task task : arrayList) {
            display += "\n" + i + ". " + task;
            i++;
        }
        formatMessage(display);
    }
}
