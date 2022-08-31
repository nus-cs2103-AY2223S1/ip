package duke;

/**
 * Represents the Main Duke program which has-a Storage, TaskList and Ui.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor for Duke program.
     */
    public Duke() throws DukeException {
        String filePath = "./data";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            throw new DukeException(ui.showLoadingError());
        }
    }

    /**
     * Handles Duke response to user.
     *
     * @param userInput input that user types into GUI.
     * @return response that duke gives.
     */
    public String getResponse(String userInput) throws DukeException {
        boolean isExit = false;
        String dukeResponse = "";
        Command c = Parser.parse(userInput);
        dukeResponse = c.execute(tasks, ui, storage);
        isExit = c.isExit();
        if (isExit) {
            System.exit(0);
        }
        return dukeResponse;
    }
}
