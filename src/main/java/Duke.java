package duke;

import java.io.IOException;
import java.util.Scanner;


/**
 * Represents a ChatBot for managing tasks of 3 types: deadline, todo and event.
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private final static String FILE_LOCATION = "./data/duke.txt";

    /**
     * Create ChatBot.
     * @param filePath
     * @throws Exception
     */
    public Duke(String filePath) throws Exception{
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.readFile());
        this.parser = new Parser();
    }

    /**
     * Run the ChatBot.
     * Receive command from the users, implement it and return the respective response.
     * @throws IOException
     */
    public void run() throws IOException{
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                isExit = c.execute(tasks, ui, storage);
                if (isExit == true) {
                    storage.saveNewChanges(tasks);
                }
            } catch (MismatchInputException e) {
                storage.saveNewChanges(tasks);
            } catch (TaskWithNoDescriptionException e) {
                storage.saveNewChanges(tasks);
            }
        }
    }

    /**
     * Start ChatBot.
     */
    public static void main(String[] args) throws Exception {
        new Duke(FILE_LOCATION).run();
    }

}

