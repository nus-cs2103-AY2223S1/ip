package duke;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class deals with interactions with the user in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class Ui {

    public Scanner scanner;

    /**
     * Initializes a Ui object with a Scanner.
     */

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input entered by the user and passes it as a String to the Parser.
     *
     * @return str The user input entered by the user.
     */

    public String readCommand() {
        String str = scanner.nextLine();
        return str;
    }

    /**
     * Prints out the welcome message when the Duke program starts.
     */

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints out the goodbye message when the Duke program ends.
     */

    public static void bye() {
        System.out.println("Sayonara, Adios!");
    }

    /**
     * Prints out the error message whenever the Duke program catches a DukeException.
     */

    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Prints out the list of tasks currently stored in the TaskList.
     */

    public static void list(ArrayList<Task> db) {
        System.out.println("Here are your list of tasks!");
        db.forEach(task -> System.out.println((db.indexOf(task) + 1)
                + ". "  + task.toString()));
        System.out.println("You have " + db.size() + " tasks in the list.");
    }
}
