package duke;

import duke.commands.Command;
import duke.exceptions.InvalidInputException;
import duke.exceptions.UnableToSaveException;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {



    private StorageFile storageFile;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up required objects, loads the data from the storage file.
     * Prints welcome message.
     */
    public Duke() {
        ui = new Ui();
        storageFile = new StorageFile();
        tasks = new TaskList(storageFile.loadTasks());
    }

    /** Runs the program until termination */
    public void run() {
        ui.showWelcomeMessage();
        runProgram();
        exit();
    }

    public void runProgram() {
        boolean hasEnded = false;
        while (!hasEnded) {
            try {
                final String userCommand = ui.getUserCommand();
                final Command command = new Parser().parseCommand(userCommand);
                command.execute(tasks, ui, storageFile);
                hasEnded = command.hasEnded();
            } catch (UnableToSaveException | InvalidInputException e) {
                ui.showMessages(e.getMessage());
            }
        }
    }

    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
