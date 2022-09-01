package duke;

import java.io.FileNotFoundException;

import duke.command.Command;

/**
 * Duke is a bot to help the user track tasks.
 */
public class Duke {

    private TaskList list;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home") + "/data/duke.txt");
        try {
            this.list = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.list = new TaskList();
        }
    }

    /**
     * Responds with a text that corresponds to the command.
     *
     * @param input User input.
     * @return Duke response.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            Command cmd = parser.getCommand();
            return cmd.execCommand(this.list, this.ui, this.storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
