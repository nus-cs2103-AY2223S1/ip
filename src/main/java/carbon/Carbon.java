package carbon;

import carbon.error.CarbonException;

/**
 * Interacts with user through CLI to keep track of tasks.
 * Requires user to provide input and call task management commands.
 */
public class Carbon {
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Creates an instance of Carbon.
     * Main constructor.
     * Initializes with required components: UI, Storage, and Parser.
     *
     * @return Carbon object.
     */
    public Carbon() {
        // init fields
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser(this.ui, this.storage);
    }

    /**
     * Returns the greeting for the user interface.
     *
     * @return Greeting to the user.
     */
    public String greet() {
        return this.ui.greet();
    }

    /**
     * Returns the log response from the parser and processer.
     *
     * @param input User text input.
     * @return Execution log.
     */
    public String getResponse(String input) {
        String log;
        try {
            log = this.parser.processCommand(input);
        } catch (CarbonException error) {
            log = error.toString();
        }
        return log;
    }
}
