package duke;

import javafx.application.Application;

/**
 * Duke is an assistant that helps keep track of deadlines, events and todos.
 */
public class Duke {
    protected final TaskList tasks;
    protected final Ui ui;
    protected final Storage storage;
    protected final Parser parser = new Parser(this);

    /**
     * The constructor that creates a Duke object.
     * @param filePath FilePath where the data stored by Duke is located.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }
}
