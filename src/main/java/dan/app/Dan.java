package dan.app;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

import dan.exceptions.DanException;
import dan.parser.Parser;
import dan.task.TaskList;
import dan.tasklistreader.TaskListReader;
import dan.ui.Ui;
import javafx.fxml.FXML;

/**
 * Dan is a personal chat bot that helps its user keep track of tasks. It currently supports the following commands:
 * 1.
 *
 * @author Daniel Lee
 * @version 0.1
 */
public class Dan {
    private TaskListReader tlr;
    private TaskList tasks;
    private Parser parser;
    private String dataFileName = "SaveData.txt"; //default save file name


    /**
     * Initialises the chat bot, Dan.
     * The application reads the data file specified at its default storage location to generate it's list of tasks
     * If the file is not found, a new data file is created.
     * <p>
     * default storage location: src/main/data
     */
    public Dan() {
        tlr = new TaskListReader(dataFileName);
        try {
            tasks = new TaskList(tlr.readTaskListFromFile());
            parser = new Parser(tasks);
        } catch (NoSuchFileException e) {
            try {
                Ui.printIndent("Task list data not found, creating new data file...");
                tlr.createFile();
                tasks = new TaskList(tlr.readTaskListFromFile());
                parser = new Parser(tasks);
                Ui.printIndent(String.format("Data file created at %s\n.", tlr.getPath()));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                Ui.printIndent("Error when creating new data file");
            } catch (DanException de) {
                Ui.printIndent(de.getMessage());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            Ui.printIndent("Error when reading current data file");
        } catch (DanException de) {
            Ui.printIndent(de.getMessage());
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
                parser.parse(input);
                isExit = parser.getIsExit();
                tlr.writeTaskListToFile(tasks);
            } catch (IOException ioe) {
                Ui.printIndent(ioe.getMessage() + "Error when saving to data file");
            }
        }
        // writes list every iteration
    }

    /**
     * Saves the task list into the save datafile.
     */
    public void saveTasks() throws IOException {
        tlr.writeTaskListToFile(tasks);
    }

    public static String greetGui() {
        return Ui.greet();
    }

    /**
     * Returns the expected response after passing through the chatbot logic
     *
     * @param input Raw user input
     * @return String containing the appropriate response after passing through the bot logic
     */
    @FXML
    String getResponse(String input) {
        return parser.parse(input);
    }

    boolean getExitStatus() {
        return parser.getIsExit();
    }

}
