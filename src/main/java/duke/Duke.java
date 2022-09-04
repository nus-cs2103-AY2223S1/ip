package duke;

import duke.io.Parser;
import duke.io.Storage;
import duke.io.TaskList;
import duke.ui.Ui;

/**
 * Duke is a personal chatbot to keep track of things.
 *
 * @author Aaron Tan
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        run();
    }


    private void run() {
        ui.introduction();
        tasks = storage.readData();
    }
    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * @param input User input to parse
     * @return Returns a String to respond to the user input.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.saveData(tasks);
        }
        return parser.process(input, tasks);
    }
}
