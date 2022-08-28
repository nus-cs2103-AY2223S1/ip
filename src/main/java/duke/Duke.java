package duke;

import java.io.FileNotFoundException;

import duke.command.Command;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for Duke.
     *
     * @param filePath The filepath in which the saved data is stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method that runs Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExited = false;
        while (!isExited) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExited = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.home") + "/data/duke.txt").run();
    }
}
