package duke;


/**
 * Encapsulates the main Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    /**
     * Creates a Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.storage);
    }


    /**
     *
     */

    public String getResponse(String input) {
        try {
            return this.parser.parse(input);
        } catch (DukeException e) {
            return ui.showLoadingError(e);
        }
    }
}
