package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.constants.ErrorMessages;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Duke class to initialise application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/saved.txt");
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser();
    }

    /**
     * Initializes a new {@code Duke} object with location
     * of the saved data
     * @param filePath path to the file with saved data
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser();
    }

    /**
     * Starts the program with welcome message and initialize saved data if any
     */
    public void run() throws DukeException {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        this.storage.run();

        while (true) {
            //Parse -> Execute -> Print Result
            String input = sc.nextLine();
            Command c = parser.parse(input);
            c.execute(tasks, this.storage, ui);
        }
    }

    /**
     * Starts the application
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data/saved.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage, ui);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
        return ErrorMessages.INVALID_COMMAND_MESSAGE;
    }

}

