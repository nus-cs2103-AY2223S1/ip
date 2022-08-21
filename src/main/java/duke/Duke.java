package main.java.duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Duke {
    /**
     * Directory and filename path on disk to save task list.
     */
    private static final String OUTPUT_DIRECTORY = "data";
    private static final String OUTPUT_FILENAME = "list.txt";

    /**
     * 'List' attribute to store inputs.
     */
    private final TaskList taskList = new TaskList();

    /**
     * `Ui` object to handle user-interface.
     */
    private final Ui ui;

    /**
     * `Storage` object to handle reading and writing task list to disk.
     */
    private final Storage storage;

    /**
     * `Parser` object to parse and handle user inputs.
     */
    private final Parser parser;

    private Duke(String directory, String filename) {
        ui = new Ui();
        storage = new Storage(directory, filename, taskList);
        parser = new Parser(taskList);
    }

    private void run() {
        // Create Scanner object for user inputs.
        Scanner sc = new Scanner(System.in);
        String userInput;

        //Boolean attribute to know if Duke is running.
        boolean runDuke = true;

        // Run Duke.
        while (runDuke && sc.hasNextLine()) {
            userInput = sc.nextLine();
            try {
                // Parse input to get command to call. Checks to user input
                // are also made here.
                BiFunction<TaskList, String, String> command =
                        parser.handleUserInputs(userInput);
                // Apply command on input.
                String output = command.apply(taskList, userInput);
                // Print any output.
                if (output.equals("exit sequence initiated")) {
                    runDuke = false;
                    ui.showUser("Bye. Hope to see you again soon!");
                    break;
                } else {
                    System.out.println(output);
                }
                // Write to disk.
                storage.writeToFile(taskList);
            } catch (DukeException e) {
                ui.showUser(e.getMessage());
            } catch (IOException e) {
                ui.showUser("Error writing to file: " + e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Main function with program logic.
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        new Duke(OUTPUT_DIRECTORY, OUTPUT_FILENAME).run();
    }
}