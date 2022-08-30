package duke.main;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.tasks.TaskList;
import javafx.scene.control.Label;

/**
 * Duke is our helper which manages the commands
 */
public class Duke {

    protected final Ui ui;
    protected final Storage storage;
    /**
     * The tasklist keeps track of all the tasks added
     */
    protected TaskList tasks;

    protected boolean isExit = false;

    /**
     * Initialises tasklist based on whether data file specified in filePath is valid
     *
     * @param filePath Filepath where data file is stored in
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            return "Unknown command received...";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
//    /**
//     * Runs Duke as long as no ByeCommand given
//     */
//    void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unknown command received...");
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//    }
}
