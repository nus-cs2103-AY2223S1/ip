package duke;

import duke.command.Command;

/**
 * Represents chatbot duke
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private static final String filePath = "./data/duke.txt";

    /**
     * Initialize duke
     *
     * @param filePath file path to store data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            try {
                storage.createFile();
            } catch (DukeException ex) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs duke
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String response = ui.readCommand();
                Command c = Parser.parse(response);
                ui.showLine();
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
        new Duke(filePath).run();
    }
}
