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
            System.out.println("list size-" + tasks.listSize());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    //@@author alvintfl-reused
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
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
    public String getWelcome() {
        ui.showWelcome();
        return ui.getResponse();
    }
    //@@author
}
