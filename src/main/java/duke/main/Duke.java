package duke.main;

import duke.command.Command;
import duke.errors.DukeException;
import duke.task.TaskList;

/**
 * Represents the Duke programme. Carries out the commands and handles exceptions
 */
public class Duke {
    private static final String FILEPATH = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;

    /**
     * Constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            String response = ui.getOutput();
            ui.resetOutput();
            if (isExit) {
                ui.showBye();
                response = ui.getOutput();
                ui.resetOutput();
                return response;
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

//    /**
//     * Runs main program
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//        ui.showBye();
//    };

//    /**
//     * Runs main method
//     * @param args
//     */
//    public static void main(String[] args) {
//        new Duke().run();
//    }

}
