package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Ui ui;
    private StorageFile storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        start();
        runCommandLoop();
        exit();
    }

    /**
     * Sets up the required objects, loads the data from the storage file.
     * Prints the welcome message.
     */
    private void start() {
        ui = new Ui();
        storage = new StorageFile(StorageFile.DEFAULT_STORAGE_FILEPATH);
        taskList = new TaskList(storage.load());
        ui.showWelcome();
    }

    /**
     * Prints the exit message and exits.
     */
    private void exit() {
        ui.showExit();
        System.exit(0);
    }

    public void runCommandLoop() {
        boolean isExit = false;
        do {
            try {
                final String userCommand = ui.getUserCommand();
                final Command command = new Parser().parseCommand(userCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showMessages(e.getMessage());
            }
        } while (!isExit);

    }

}
