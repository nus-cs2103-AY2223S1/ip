package Duke;

import Duke.Command.Command;
import Duke.Command.InvalidCommandException;
import Duke.FileStorage.InvalidStorageException;
import Duke.FileStorage.Storage;

/**
 * This class represents the chatbot interface that allows
 * user to interact with the todo list.
 */
public class Duke {

    /** The handler for reading and writing the tasks list from and to file. */
    private Storage storage;

    /** The list of tasks tracked by Duke. */
    private TaskList tasks;

    /** The ui which the user interact with. */
    private Ui ui;

    /**
     * Constructs the Duke todo list agent.
     * 
     * @param filePath The file path where the list will be written to and
     * retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showResponse(String.format("Here is the list that you have saved previously:\n%s", 
                    tasks.toString()));
            if (!storage.getWarnings().isEmpty()) {
                ui.showWarning(storage.getWarnings());
            }
        } catch (InvalidStorageException e) {
            ui.showErr(e.getMessage());
            tasks = new TaskList();
        } 
    }

    /**
     * Runs the main logic of Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                ui.showErr(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showErr(String.format("Thats an invalid number!\n" 
                        + "Please enter a number between %d and %d", 1, tasks.getNumOfTasks()));
            } finally {
                ui.showHorizontalLineLong();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

}