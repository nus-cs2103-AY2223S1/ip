package duke;

import java.io.IOException;

/**
 * A task-keeping chatbot with a command line interface.
 *
 * @author Conrad
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the <code>Duke</code> chatbot.
     *
     * @param filePath The URL location of the local storage text file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the welcome message from the bot.
     *
     * @return String containing the bot's welcome message.
     */
    public String getWelcome() {
        return this.ui.showWelcome();
    }

    /**
     * Returns the response from the bot after processing the user input.
     *
     * @param userResponse The input from the user.
     * @return String containing the response from the bot.
     */
    public String getResponse(String userResponse) {
        try {
            Command command = Parser.parseUserResponse(userResponse, this.storage, this.ui, this.tasks);
            return command.execute();
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }
}
