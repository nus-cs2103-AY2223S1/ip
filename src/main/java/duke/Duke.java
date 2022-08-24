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
    public Duke() {
        String filePath = "./data";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

     /** Handles Duke response to user.
     *
     * @param userInput input that user types into GUI.
     * @return response that duke gives.
     */
    public String getResponse(String userInput) {
        boolean isExit = false;
        String dukeResponse = "";
        try {
            Command c = Parser.parse(userInput);
            dukeResponse = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                System.exit(0);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return dukeResponse;
    }
}
