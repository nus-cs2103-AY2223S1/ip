package duke;

import duke.command.Command;

import java.io.IOException;

/**
 * Represents the Duke chatbot that stores users tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke chatbot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(System.getProperty("user.home"));
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
