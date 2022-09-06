package duke;

import duke.command.Command;

/**
 * Represents chatbot duke.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Initialize duke.
     *
     * @param filePath file path to store data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.createFile();
            } catch (DukeException ex) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns duke's response to user's input.
     *
     * @param input user's input.
     * @return response.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = parser.parse(input);
            response = c.execute(tasks, ui, storage, parser);
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    /**
     * Returns duke's greeting.
     *
     * @return greeting.
     */
    public String getGreeting() {
        return ui.greet();
    }
}
