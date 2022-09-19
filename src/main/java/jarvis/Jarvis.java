package jarvis;

import java.io.IOException;

import jarvis.gui.Main;
import jarvis.exception.JarvisException;
import jarvis.task.TaskList;
import javafx.application.Application;

/**
 * Jarvis is the main class where the program is initialised.
 */
public class Jarvis {
    private static Parser parser;

    /**
     * Main method for program
     * @param args
     */
    public static void main(String[] args) {

        String filePath = "data/taskList.txt";

        TaskList tasks;
        Storage storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks);

        Application.launch(Main.class, args);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Passes the input to the parser and returns the response
     */
    public String getResponse(String input) throws JarvisException {
        return parser.readCommand(input);
    }

}
