package duke;

import java.util.Scanner;

/**
 * The Main class of Duke. Runs the entire program.
 */
public class Duke {
    /** The storage component. */
    private Storage storage;

    /** The tasks component. */
    private TaskList tasks;

    /** The parse component. */
    private Parser parser;

    /** The ui component. */
    private Ui ui;

    /**
     * The class constructor for Duke. Initializes all necessary
     * objects for usage. Reads and loads saved tasks if available.
     *
     * @param filePath of the file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.loadFile(new TaskList());
        this.parser = new Parser(tasks);
    }

    /**
     * Initializes the core functionality of Duke. One-half of the decision making
     * tree of Duke. Application terminates when it encounters "bye".
     */
    public void run() {
        this.ui.start();
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                ui.printList(this.tasks);
            } else {
                try {
                    this.parser.parse(input);
                    storage.writeToFile("./data", this.tasks);
                } catch (DukeException e) {
                    ui.showError(e);
                }
            }
            input = myObj.nextLine();
        }

        this.ui.close();
        myObj.close();
    }

    /**
     * Starts the Duke application.
     *
     * @param args of user input.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
