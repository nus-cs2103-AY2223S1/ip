package duke;

import duke.commands.Command;

/**
 * Represents the chatbot.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

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

    private void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
