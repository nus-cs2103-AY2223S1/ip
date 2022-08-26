package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Duke class to initialise application
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes a new {@code Duke} object with location
     * of the saved data
     * @param filePath path to the file with saved data
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    /**
     * Starts the program with welcome message and initialize saved data if any
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        this.storage.run();
        tasks = new TaskList(storage.loadData());
        Parser parser = new Parser(this.ui, tasks);

        while (true) {
            String input = sc.nextLine();
            Command c = parser.parse(input);
            c.execute(tasks, this.storage);
        }
    }

    /**
     * Starts the Terminal application
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        new Duke("saved.txt").run();
    }
}

