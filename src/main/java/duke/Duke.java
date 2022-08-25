package duke;

/**
 * Main class that runs Duke Bot
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    /**
     * Constructor to initialize Duke object
     * @param filePath Location where data is and will be stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile(), storage);
        parser = new Parser(tasks);
    }

    /**
     * Main logic and sequence of project
     */
    public void run() {
        ui.greeting();
        parser.parseFunc();
        ui.farewell();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public static void echo(String userInput) {
        System.out.println(userInput);
    }
}
