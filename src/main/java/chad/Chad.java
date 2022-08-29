package chad;

import chad.exception.ChadException;
import chad.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main way for users to interact with Chadbot
 */
public class Chad {

    /**
     * Main Function of Chad
     * @param args Reads input
     * @throws IOException Thrown when helper file cannot be open
     * @throws ChadException Thrown when invalid error occurs
     */
    public static void main(String[] args) throws IOException, ChadException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = Storage.initializeArrayList();
        Ui.greet();

        while (true) {
            String userInput = sc.nextLine();
            Parser.readUserInput(taskList, userInput);
        }
    }
}
