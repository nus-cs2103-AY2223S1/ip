package duke;

import duke.commands.Command;

/**
 * Represents the chatbot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a chatbot.
     *
     * @param filePath The file path of the storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList currTasks;
        try {
            currTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            currTasks = new TaskList();
        }
        tasks = currTasks;
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    protected String getGreeting() {
        return ui.showGreeting();
    }
}
