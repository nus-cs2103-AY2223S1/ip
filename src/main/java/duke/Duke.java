package duke;

/**
 * Duke is an assistant that helps keep track of deadlines, events and todos.
 */
public class Duke {
    protected final TaskList tasks;
    protected final Ui ui;
    protected final Storage storage;

    /**
     * The constructor that creates a Duke object.
     * @param filePath FilePath where the data stored by Duke is located.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the jamie program.
     */
    public void run() {
        ui.welcome();
        Parser parser = new Parser(this);
        parser.start();
        ui.bye();
    }

    public static void main(String[] args) {
        new Duke("Data/DukeTasks.txt").run();
    }
}
