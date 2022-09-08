package arc;

/**
 * Encapsulates the main Duke program
 */
public class Duke {
    protected static final String LIST_OF_ARC_COMMANDS = "\naRCommands:\n"
            + "\tbye\n"
            + "\tlist\n"
            + "\ttodo [title]\n"
            + "\tdeadline [title] /by [deadline]\n"
            + "\tevent [title] /at [time]\n"
            + "\tmark [index]\n"
            + "\tunmark [index]\n"
            + "\tdelete [index]\n"
            + "\tfind [keyword]";

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.storage = new Storage("data/aRC.txt");
        this.tasks = new TaskList(this.storage.load());
        this.parser = new Parser(this.storage, this.tasks);
    }

    public String getResponse(String input) {
        try {
            return this.parser.parse(input);
        } catch (DukeException e) {
            return UI.getException(e);
        }
    }
}
