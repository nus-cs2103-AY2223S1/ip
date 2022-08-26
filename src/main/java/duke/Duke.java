package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Chatbot to read user inputs and manage a tasks list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a <code>Duke</code> instance and attempt to retrieve the saved tasks list stored locally.
     * If the retrieval succeeds, initializes a <code>TaskList</code> with the saved tasks in it.
     * If the retrieval fails, initializes a <code>TaskList</code>.
     *
     * @param filePath Path of the file relative to the root directory.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.printWelcome();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.printSuccessfulLoad();
        } catch (DukeException e) {
            ui.printFailedLoad();
            tasks = new TaskList();
        }
    }

    /**
     * Runs <code>Duke</code> to start taking inputs.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}
