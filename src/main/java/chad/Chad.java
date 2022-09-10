package chad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chad.exception.ChadException;
import chad.task.Task;

/**
 * Main way for users to interact with Chadbot
 */
public class Chad {
    private static ArrayList<Task> taskList;

    /**
     * Initialize a new task list
     */
    public Chad() {
        try {
             taskList = Storage.initializeArrayList();
        } catch (ChadException ce) {
            System.out.println(ce.getMessage());
        }
    }

    /**
     * Get a string response by parsing it into read command
     * @param input input
     * @return String response
     */
    public String getResponse(String input) {
        String output;
        try {
            output = Parser.readCommand(taskList, input);
        }catch (ChadException ce) {
            output = ce.getMessage();
        }
        return output;
    }

    /**
     * Main Function of Chad
     */
    public static void run() {
        Scanner sc = new Scanner(System.in);
        Utility.printToConsole(Ui.greet());
        while (true) {
            String userInput = sc.nextLine();
            String output;
            try {
                output = Parser.readCommand(taskList, userInput);
            } catch (ChadException ce) {
                output = ce.getMessage();
            }
            Utility.printToConsole(output);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws ChadException {
        run();
    }
}
