package duke;

import java.util.ArrayList;
import java.util.List;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the main functions of Duke chatbot and initializes require variables.
 */
public class Duke {

    private List<Task> list = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Initialises Duke chatbot.
     * @param filePath directory of where tasks are saved.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    //@@author alvintfl-reused
    /**
     * Gets a response from chatbot to user input
     * @param input user input
     * @return response from chatbot
     */
    public Response getResponse(String input) {
        try {
            ui.validate(input);
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return ui.getResponse();
    }

    /**
     * Returns welcome message to be printed in GUI
     * @return welcome message
     */
    public Response getWelcome() {
        ui.showWelcome(storage);
        return ui.getResponse();
    }
    //@@author
}
