package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Duke program.
 */
public class Duke {
    private static boolean isRunning;

    /**
     * The method used to run the Duke program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            Storage.loadData();
        } catch (IOException e) {
            System.out.println("Error: Failed to access data");
            return;
        }

        Ui.displayStartUpText();

        Scanner scanner = new Scanner(System.in);

        isRunning = true;
        while (isRunning) {
            Ui.generateLine();
            String input = scanner.nextLine();
            Ui.generateLine();

            try {
                isRunning = Parser.parseInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        Ui.displayExitText();

        try {
            Storage.saveData();
        } catch (IOException e) {
            System.out.println("Error: Failed to access data");
            return;
        }
    }
}
