package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Duke class to initialise application
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Empty constructor
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("saved.txt");
        this.tasks = new TaskList(storage.loadData());
        this.parser = new Parser(tasks);
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
        this.parser = new Parser(tasks);
    }

    /**
     * Starts the program with welcome message and initialize saved data if any
     */
    public void run() {
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
     * Starts the Terminal application
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) {
        new Duke("./data/saved.txt").run();
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, this.storage, ui);
    }

}

