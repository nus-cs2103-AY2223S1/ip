package qoobee;

/**
 * Contains the main class which is primarily run.
 */
public class Qoobee {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Qoobee object that contains a ui, storage, parser and tasklist.
     */
    public Qoobee() {
        this.ui = new Ui();
        this.storage = new Storage("TaskList.txt");
        try {
            tasks = new TaskList(storage);
            storage.loadFile();
            parser = new Parser(ui, tasks);
        } catch (QoobeeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(storage);
        }
    }


    /**
     * Gets the response given an input by the user.
     * @param input The input to be parsed.
     * @return The String result after parsing.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}
