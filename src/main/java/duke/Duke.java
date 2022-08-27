package duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Defines <Code>Duke</Code> class.
 * <p>Main class for the Duke application.</p>
 */
public class Duke {
    /** Directory path on disk to find/save task list. */
    private static final String OUTPUT_DIRECTORY = "data";

    /** Filename to save task list on disk. */
    private static final String OUTPUT_FILENAME = "list.txt";

    /** <Code>TaskList</Code> to store <Code>Tasks</Code> created by user. */
    private final TaskList taskList = new TaskList();

    /** <Code>Ui</Code> to handle user-interface. */
    private final Ui ui;

    /** <Code>Storage</Code> to handle reading and writing task list to disk. */
    private final Storage storage;

    /** <Code>Parser</Code> to parse and handle user inputs. */
    private final Parser parser;

    /**
     * Constructor of <Code>Duke</Code> object.
     * @param directory Directory path on disk to find/save task list.
     * @param filename  Filename to save task list on disk.
     */
    private Duke(String directory, String filename) {
        ui = new Ui();
        storage = new Storage(directory, filename, taskList);
        parser = new Parser(taskList);
    }

    /**
     * Runs <Code>Duke</Code> program and begin accepting user inputs.
     * <p>
     *  While running, the program does the following in repeat:
     *  <ol>
     *      <li>Take in user input.</li>
     *      <li>Execute command depending on given user input.</li>
     *      <li>Print output (if any).</li>
     *      <li>Save tasks onto hard disk (if changes were made).</li>
     *  </ol>
     * </p>
     */
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
     * Main function.
     * @param args Command line arguments not used.
     */
    public static void main(String[] args) {
        new Duke(OUTPUT_DIRECTORY, OUTPUT_FILENAME).run();
    }
}
