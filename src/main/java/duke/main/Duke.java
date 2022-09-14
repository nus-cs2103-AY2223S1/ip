package duke.main;

import duke.command.Command;

/**
 * Class for Duke.
 */
public class Duke {
    protected boolean isExit = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/dude.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(storage);
        }
    }

    /**
     * Returns the response from Duke based on user input.
     *
     * @param command instructions from the user.
     * @return response from Duke.
     */
    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.reply;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns a Duke's greeting.
     * @return greeting from Duke.
     */
    public String greet() {
        return ui.showWelcome();
    }
}
