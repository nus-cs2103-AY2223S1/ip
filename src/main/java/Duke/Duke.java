package duke;

/**
 * Main class for Duke application.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        storage.readFromFile(taskList);
    }

    /**
     * Returns the response corresponding to user input.
     *
     * @param input user input.
     * @return response as a String.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }
}


