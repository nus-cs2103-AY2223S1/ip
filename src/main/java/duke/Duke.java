package duke;

/**
 * Main class that runs Duke Bot
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;
    protected String filePath = "data/duke.txt";

    /**
     * Constructor to initialize Duke object
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile(), storage);
        parser = new Parser(tasks, ui);
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
        new Duke().run();
    }


    public static void echo(String userInput) {
        System.out.println(userInput);
    }


    public void end() {
        ui.farewell();
        System.exit(0);
    }
    public String getResponse(String input) {
        if (input.equals("bye")) {
            end();
        }
        return parser.guiParseFunc(input);
    }
}
