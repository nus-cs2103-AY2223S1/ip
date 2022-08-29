import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main way for users to interact with Chadbot
 */
public class Chad {

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
