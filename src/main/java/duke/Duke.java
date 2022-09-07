package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.data.Storage;
import duke.data.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Ui;

/**
 * Takes in input and chats with user.
 *
 * @author Jicson Toh
 */
public class Duke {
    private static final String FILE_PATH = "data/data.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Default constructor if no file path specified
     */
    public Duke() {
        this(FILE_PATH);
    }

    /**
     * Constructor for Duke Class.
     *
     * @param filePath file path of data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Executes the command input by the user.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            storage.saveData(tasks);
            String action = input.nextLine();
            try {
                Command command = parser.parseCommand(action);
                String result = command.execute(tasks, ui, storage);
                System.out.println(result);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Outputs the string of the executed command.
     *
     * @param action action to be executed.
     * @return returns the string of command executed.
     */
    public String getResponse(String action) {
        try {
            Command command = parser.parseCommand(action);
            String result = command.execute(tasks, ui, storage);
            storage.saveData(tasks);
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
