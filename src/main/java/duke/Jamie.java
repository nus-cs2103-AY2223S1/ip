package duke;

/**
 * Jamie is an assistant that helps keep track of deadlines, events and todos.
 */
public class Jamie {
    protected final TaskList tasks;
    protected final Ui ui;
    protected final Storage storage;

    /**
     * The constructor that creates a Jamie object.
     * @param filePath FilePath where the data stored by Jamie is located.
     */
    public Jamie(String filePath) {
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
        new Jamie("Data/JamieTasks.txt").run();
    }
}
