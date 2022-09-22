package ado;

import java.util.ArrayList;
import java.util.List;

import ado.command.Command;
import ado.storage.Storage;
import ado.task.Task;
import ado.task.TaskList;
import javafx.application.Platform;

/**
 * Represents the main functions of Ado chatbot and initializes require variables.
 */
public class Ado {

    private List<Task> list = new ArrayList<>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;

    /**
     * Initialises Ado chatbot.
     * @param filePath directory of where tasks are saved.
     */
    public Ado(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AdoException e) {
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
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                Platform.exit();
            }
        } catch (AdoException e) {
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
