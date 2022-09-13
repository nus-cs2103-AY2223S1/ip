package duke;

/**
 * The duke.Main class of Duke. Runs the entire program.
 */
public class Duke {
    /** The storage component. */
    private final Storage storage;

    /** The tasks component. */
    private final TaskList tasks;

    /** The parse component. */
    private final Parser parser;

    /** The ui component. */
    private final Ui ui;

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
     *
     * @param input of the user.
     * @return String output of Duke's response.
     */
    public String run(String input) {
        if (input.equals("bye")) {
            storage.writeToFile("/data", this.tasks);
            System.exit(0);
        }
        if (input.equals("list")) {
            return ui.printList(this.tasks);
        }
        try {
            String response = this.parser.parse(input);
            storage.writeToFile("./data", this.tasks);
            return response;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Returns a string where Duke introduces itself to the user.
     *
     * @return String output of salutation.
     */
    public String start() {
        return this.ui.start();
    }

    /**
     * Returns the response of Duke after parsing the user input.
     *
     * @return String output of Duke's response.
     */
    public String getResponse(String input) {
        return this.run(input.trim());
    }

}
