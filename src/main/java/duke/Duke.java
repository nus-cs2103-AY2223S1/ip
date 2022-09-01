package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Stores the main logic of the AIlfred bot.
 * @author Jason
 */
public class Duke {
    private static final String PATH_FILE = "src/data/duke.txt";
    private static final String PATH_DIRECTORY = "src/data";
    private Scanner scanner;
    private TaskList taskList;
    private Storage storage;
    private boolean isLoaded;

    /**
     * Initializes the application.
     */
    public Duke(String filePath, String directoryPath) throws DukeException, IOException {
        try {
            storage = new Storage(PATH_FILE, PATH_DIRECTORY);
            taskList = new TaskList(storage.load());
            isLoaded = true;
        } catch (DukeException | IOException e) {
            storage = new Storage(PATH_FILE, PATH_DIRECTORY);
            taskList = new TaskList(new ArrayList<>(100));
            isLoaded = false;
        }
    }

    public Duke() throws DukeException, IOException {
        this(PATH_FILE, PATH_DIRECTORY);
    }

    /**
     * Listens to System.in for input.
     */
    public void listen() throws DukeException, IOException {
        String input; // initializing the input

        // Reading user inputs
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            Command command = Parser.parseCommand(input);
            command.run(taskList, storage);
        }
        scanner.close();
    }

    /**
     * Provides the String message to be displayed.
     * @param input String to be parsed.
     * @return Message to be displayed.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            String res = c.run(taskList, storage);
            System.out.println(res);

            return res;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getGreeting() {
        String message = "";
        if (isLoaded) {
            message += "Directory located... \n"
                    + "Previous save file located, loading contents of save file... \n";

        } else {
            message += "Creating a directory to store save file... \n"
                    + "Creating a new save file... \n";
        }

        message += Ui.greet();
        return message;
    }
}
