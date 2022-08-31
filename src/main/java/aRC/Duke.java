package arc;

/**
 * Encapsulates the main Duke program
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage("data/aRC.txt");
        this.tasklist = new TaskList(this.storage.load());
        this.parser = new Parser(this.storage, this.tasklist);
    }

    public String getResponse(String input) {
        try {
            return this.parser.parse(input);
        } catch (DukeException e) {
            return UI.getException(e);
        }
    }
}
