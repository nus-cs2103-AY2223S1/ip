package main.java;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Duke {
    /**
     * 'List' attribute to store inputs.
     */
    private static final TaskList taskList = new TaskList();

    /**
     * Boolean attribute to know if Duke is running.
     */
    private static Boolean runDuke = false;

    /**
     * `Ui` object to handle user-interface.
     */
    private static Ui ui;

    /**
     * `Storage` object to handle reading and writing task list to disk.
     */
    private static Storage storage;

    /**
     * `Parser` object to parse and handle user inputs.
     */
    private static Parser parser;

    /**
     * Main function with program logic.
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        String OUTPUT_DIRECTORY = "data";
        String OUTPUT_FILENAME = "list.txt";

        runDuke = true;
        ui = new Ui();
        storage = new Storage(OUTPUT_DIRECTORY, OUTPUT_FILENAME, taskList);
        parser = new Parser(taskList);

        // Create Scanner object for user inputs.
        Scanner sc = new Scanner(System.in);
        String userInput;

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
}