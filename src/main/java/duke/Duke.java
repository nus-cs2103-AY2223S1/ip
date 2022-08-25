package duke;

/**
 * Main Class of this program
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for Duke. Initializes tasklist and parser.
     */
    public Duke() {
        this.taskList = new TaskList(Storage.load());
        this.parser = new Parser(this.taskList);
    }

    /**
     * Runs the parser to parse user input
     */
    public void run() throws DukeException {
        Ui.showWelcome();

        while (parser.isScanning()) {
            parser.parse();
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
