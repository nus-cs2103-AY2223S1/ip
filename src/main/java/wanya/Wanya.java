package wanya;

import wanya.parser.Parser;
import wanya.task.TaskList;
import wanya.ui.Ui;


/**
 * Represents a Wanya bot that act as a task checker.
 */
public class Wanya {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates a Wanya object and initialise Ui, Storage and TaskList.
     */
    public Wanya() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (WanyaException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }


    /**
     * Parse the command input by user and returns a response.
     *
     * @param input Command input by user.
     * @return String message in response to user's input.
     */
    public String getResponse(String input) {
        return Parser.parseCommand(input, tasks, ui, storage);
    }
}


