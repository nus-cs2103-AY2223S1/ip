package duke;

import java.io.File;

/**
 * Represents the bot that can interact with user based on the command being input.
 */
public class Duke {

    public static final String LINE = "____________________________________________________________";
    public static int count = 0;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private  Parser parser;

    /**
     * Constructs a Duke bot.
     */
    public Duke() {
    };

    /**
     * Constructs a Duke bot.
     * @param filePath the file path in which the data is being stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Runs the bot.
     */
    public void run() {
        storage.load_task(new File("duke.txt"));
        parser.respond();
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
