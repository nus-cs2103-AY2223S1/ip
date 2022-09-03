package cheese;

import cheese.command.Command;
import cheese.data.TaskList;
import cheese.exception.CheeseException;
import cheese.parser.Parser;
import cheese.storage.Storage;

/**
 * Represents a command line interface used to manage tasks.
 */
public class Cheese {
    /** List of tasks. */
    private TaskList taskList;

    /** Storage to interact with save file. */
    private Storage storage;

    /**
     * Constructs an instance of <code>Cheese</code>.
     *
     * @param filePath File path of save file.
     */
    public Cheese(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (CheeseException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs program until user enters command to exit.
     */
    //    public void run() {
    //        ui.showWelcome();
    //        boolean isExit = false;
    //        while (!isExit) {
    //            try {
    //                String fullCommand = ui.readCommand();
    //                Command command = Parser.parse(fullCommand);
    //                command.execute(taskList, storage, ui);
    //                isExit = ByeCommand.isBye(command);
    //            } catch (CheeseException e) {
    //                ui.showError(e.getMessage());
    //            }
    //        }
    //    }
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, storage);
        } catch (CheeseException e) {
            return e.getMessage();
        }
    }
}
