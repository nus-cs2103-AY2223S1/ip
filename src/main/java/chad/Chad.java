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
    public Chad() {
        try {
             taskList = Storage.initializeArrayList();
        } catch (ChadException ce) {
            System.out.println(ce.getMessage());
            taskList = new ArrayList<>();
        }

    }
    /**
     * Main Function of Chad
     * @throws IOException Thrown when helper file cannot be open
     * @throws ChadException Thrown when invalid error occurs
     */
    public static void run() throws IOException, ChadException {
        Scanner sc = new Scanner(System.in);
        Ui.greet();

        while (true) {
            String userInput = sc.nextLine();
            Parser.readUserInput(taskList, userInput);
        }
    }

    public String getResponse(String input) {
        String output = Parser.readUserInput(taskList, input);
        return output;

    }

    public static void main(String[] args) throws ChadException, IOException {
        run();
    }
}
