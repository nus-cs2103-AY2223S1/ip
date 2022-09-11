package duke;

import java.io.IOException;

/**
 * Duke bot object class, which has a task list, a storage space and a user interface.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Undo undo;

    /**
     * Creates a Duke object with the given filepath.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/data/tasks.txt");
        undo = new Undo();
        TaskList currentTaskList;
        try {
            currentTaskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            currentTaskList = new TaskList();
        }
        this.tasks = currentTaskList;
    }

    /**
     * Gets string of welcome message from Duke.
     *
     * @return string of welcome message from Duke.
     */
    public String showWelcomeMessage() {
        return this.ui.showWelcome();
    }

    /**
     * Gets Duke response for the user's input.
     *
     * @param input of what the user types.
     * @return a string of duke's response.
     * @throws DukeException if invalid user input.
     * @throws IOException   if file not found.
     */
    public String getResponse(String input) throws IOException {
        return Parser.parse(input, tasks, ui, storage, undo);
    }
}
