package dan;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import java.util.Scanner;

/**
 * Dan is a personal chat bot that helps its user keep track of tasks. It currently implements the following commands:
 * 1.
 *
 * @author Daniel Lee
 * @version 0.1
 */
public class Dan {
    private TaskListReader tlr;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initialises the chat bot, Dan.
     * The application reads the data file specified at its default storage location to generate it's list of tasks
     * If the file is not found, a new data file is created.
     *
     * default storage location: src/main/data
     *
     * @param fileName Name of the data storage file
     */
    public Dan(String fileName) {
        tlr = new TaskListReader(fileName);
        try {
            tasks = new TaskList(tlr.readTaskListFromFile());
            parser = new Parser(tasks);
        } catch (NoSuchFileException e) {
            try {
                Ui.printIndent("Task list data not found, creating new data file...");
                tlr.createFile();
                Ui.printIndent(String.format("Data file created at %s\n Please start me again.", tlr.getPath()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                Ui.printIndent("Error when creating new data file");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Ui.printIndent("Error when reading current data file");
        }
    }

    /**
     * Runs the chat bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = sc.nextLine().strip();
                isExit = parser.parse(input);
                tlr.writeTaskListToFile(tasks);
            } catch (IOException ioe) {
                Ui.printIndent(ioe.getMessage() + "Error when creating saving to data file");
            }
        }
        // writes list every iteration
    }

    /**
     * The main driver of the chatbot.
     *
     * @param args Command-line commands for the application
     */
    public static void main(String[] args) {
        new Dan("TestData1.txt").run();
    }
}
