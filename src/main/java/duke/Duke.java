package duke;

/**
 * Duke is a program that helps uses keep track of their tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        try {
            String filePath = "data/duke.txt";
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input, tasks, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
