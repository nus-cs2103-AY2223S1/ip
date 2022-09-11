package chad;

import java.util.ArrayList;
import java.util.Scanner;

import chad.exception.ChadException;
import chad.task.Task;

/**
 * Main way for users to interact with Chadbot.
 *
 */
public class Chad {
    private static ArrayList<Task> taskList;

    /**
     * Initialize a new task list with either existing
     * storage.
     *
     */
    public Chad() {
        try {
            taskList = Storage.initializeTaskList();
        } catch (ChadException ce) {
            Utility.printToConsole(ce.getMessage());
        }
    }

    /**
     * Get a string response by parsing it into read command
     *
     * @param input user input
     * @return response from chad bot
     */
    public static String getResponse(String input) {
        try {
            return Parser.readCommand(taskList, input);
        } catch (ChadException ce) {
            return ce.getMessage();
        }
    }

    /**
     * Runs the Chad bot on command line
     *
     */
    public static void run() {
        Scanner sc = new Scanner(System.in);
        Utility.printToConsole(Ui.greet());
        while (true) {
            String userInput = sc.nextLine();
            String output = getResponse(userInput);
            Utility.printToConsole(output);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    /**
     * Main function to run chad bot on the command line
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        run();
    }
}
