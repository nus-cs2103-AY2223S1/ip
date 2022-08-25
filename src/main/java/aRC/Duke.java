package arc;

/**
 * Encapsulates the main Duke program
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    private UI ui;

    /**
     * Constructor for Duke
     * @param filePath String representing the relative path of the datafile
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(this.storage.load());
        this.ui = new UI();
        this.parser = new Parser(this.storage, this.tasklist, this.ui);
    }

    public static void main(String[] args) {
        new Duke("data/aRC.txt").run();
    }

    /**
     * Runs the program
     */
    public void run() {
        this.ui.hello();
        String input = this.ui.readInput();

        // Keeps reading user input until the user types "bye"
        while (!input.equals("bye")) {
            try {
                this.parser.parse(input);
            } catch (DukeException e) {
                this.ui.printException(e);
            }

            input = this.ui.readInput();
        }

        this.ui.bye();
    }
}
