package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.gui.Ui;
import duke.utils.Parser;
import duke.utils.Storage;

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
    private boolean isExit = false;

    /**
     * Initializes the application.
     */
    public Duke() throws DukeException, IOException {
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

    /**
     * Listens to System.in for input.
     */
    public void listen() throws DukeException, IOException {
        // initializing the input
        String input;

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
            assert(Parser.parseCommand(input) instanceof Command);
            Command c = Parser.parseCommand(input);
            if (c instanceof ExitCommand) {
                isExit = true;
            }

            String res = c.run(taskList, storage);
            System.out.println(res);

            return res;
        } catch (InputMismatchException | IndexOutOfBoundsException
                 | NumberFormatException | NullPointerException
                 | DukeException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Provides the greeting message for Duke
     * to return in a dialog box on initialization.
     * @return Greeting message.
     */
    public String getGreeting() {
        return Ui.greet(isLoaded);
    }

    /**
     * Checks if exit command has been executed.
     * @return State of current command, if current command is the exit command or not.
     */
    public boolean isExitCommand() {
        return this.isExit;
    }
}
