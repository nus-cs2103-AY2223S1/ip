import java.io.IOException;
import java.lang.IllegalArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Executes the programme
 */
public class Duke {

    public static void main(String[] args) {
        System.out.println("Sup bro! My name is Candice.");
        System.out.println("I'm here to help you track your tasks!");

        Scanner scanner = new Scanner(System.in); // Scans input
        String input = scanner.nextLine();

        String currentDir = System.getProperty("user.dir"); // Current directory
        Path dataDir = Paths.get(currentDir, "data");
        Path tasks = Paths.get(currentDir, "data", "tasks.txt");

        // Creating the data directory if it does not exist
        if (!Files.exists(dataDir)) {
            try {
                Files.createDirectory(dataDir);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        // Creating the task list text file if it does not exist
        if (!Files.exists(tasks)) {
            try {
                Files.createFile(tasks);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        ArrayList<Task> taskList = TaskList.parseTaskListText();

        while (!input.equals("bye")) {
            try {
                Action action = Action.actionParser(input);
                action.resolve(taskList);
            } catch (UnknownActionException | EmptyTaskNameException | InvalidFormattingException |
                    EmptyTimingException | IllegalArgumentException | InvalidDateException |
                    InvalidTimeException e) {
                System.out.println(e);
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye bro. See you soon.");
    }
}
