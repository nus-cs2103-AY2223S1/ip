import java.io.IOException;

import java.lang.IllegalArgumentException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Executes the programme
 */
public class Duke {
    TaskList taskList;

    public Duke(Path storagePath) {
        Storage storage = new Storage(storagePath);
        this.taskList = new TaskList(storage);
        this.taskList.parseTaskListText();
    }

    public void run() {
        Ui.printMessageForStartingUp();

        Scanner scanner = new Scanner(System.in); // Scans input
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                Command command = Parser.parse(input);
                command.resolve(taskList);
            } catch (EmptyTaskNameException | EmptyTimingException | IllegalArgumentException | InvalidDateException
                    | InvalidFormattingException | InvalidTimeException | UnknownCommandException e) {
                System.out.println(e);
            }

            input = scanner.nextLine();
        }

        Ui.printMessageForShuttingDown();
    }

    public static void main(String[] args) {
        Path storagePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");

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

        new Duke(storagePath).run();
    }
}
