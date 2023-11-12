package duke;


/**
 * Main program for running Duke, a bot that keeps track of tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke bot
     *
     */

    public Duke() {
        String home = System.getProperty("user.home");
        ui = new Ui();
        storage = new Storage(home + "/Documents/ip/list.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command c = new Parser().parseCommand(input);
            String msg = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                assert isExit: "program trying to exit without bye command";
                System.exit(0);
            }
            return msg;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
