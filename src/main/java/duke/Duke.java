package duke;

/**
 * Duke program for keeping track of Tasks.
 */
public class Duke {
    protected static boolean terminate = false;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    /**
     * Duke constructor.
     *
     * @param filePath filePath of file to store Task information.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath, "data/temp.txt");
        ui = new Ui();
        tasks = new TaskList(ui, storage.startUp(), storage);
        parser = new Parser(tasks, ui);
    }

    /**
     * Starts Duke program.
     */
    public void run() {
        ui.greet();
        parser.takeUserInput();
    }

    /**
     * Initialises files and calls method to start Duke program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }
}
