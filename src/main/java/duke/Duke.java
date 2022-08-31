package duke;

import java.io.IOException;
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

    /**
     * Initializes the application.
     */
    public void initialize() throws DukeException, IOException {
        scanner = new Scanner(System.in);
        Ui.greet();
        storage = new Storage(PATH_FILE, PATH_DIRECTORY);
        taskList = new TaskList(storage.load());

        listen();
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
}
