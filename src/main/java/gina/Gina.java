package gina;

import gina.commands.Command;

/**
 * Represents the chatbot.
 */
public class Gina {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a chatbot.
     *
     * @param filePath The file path of the storage.
     */
    public Gina(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList currTasks;
        try {
            currTasks = new TaskList(storage.load());
        } catch (GinaException e) {
            ui.showError(e.getMessage());
            currTasks = new TaskList();
        }
        tasks = currTasks;
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (GinaException e) {
            return ui.showError(e.getMessage());
        }
    }

    protected String getGreeting() {
        return ui.showGreeting();
    }
}
