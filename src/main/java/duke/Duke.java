package duke;

import duke.command.Command;

/**
 * Duke is a Chatbot that is able to store and keep track of tasks inputted by the user.
 */
public class Duke {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
