package duke;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates an instance of Duke with the given file path.
     *
     * @param filePath Path should point to existing saved tasks or where tasks should be saved.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage);
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input.
     * @return Bot response.
     */
    String getResponse(String input) {
        try {
            return Parser.parse(input, tasks);
        } catch (DukeException e) {
            return Ui.showError(e.getMessage());
        }
    }
}
