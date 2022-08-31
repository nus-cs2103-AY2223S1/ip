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

    public String greet() {
        return this.ui.greet();
    }

    public String getResponse(String input) {
        String log;
        try {
            log = this.parser.process(input);
        } catch (CarbonException error) {
            log = error.toString();
        }
        return log;
    }
}
