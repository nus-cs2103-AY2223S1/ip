package Duke;

import Duke.Command.Command;
import Duke.Command.InvalidCommandException;
import Duke.FileStorage.InvalidStorageException;
import Duke.FileStorage.Storage;
import Duke.Main;

/**
 * This class represents the chatbot interface that allows
 * user to interact with the task list.
 */
public class Duke {

    /** The handler for reading and writing the tasks list from and to file. */
    private Storage storage;

    /** The list of tasks tracked by Duke. */
    private TaskList tasks;

    /** The ui which the user interact with. */
    private Ui ui;

    /**
     * Constructs the Duke task list agent.
     * 
     * @param filePath The file path where the list will be written to and
     * retrieved from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        assert storage != null : "Storage should be created";
        try {
            tasks = new TaskList(storage.load());
            if (!storage.getWarnings().isEmpty()) {
                ui.showWarning(storage.getWarnings());
            }
        } catch (InvalidStorageException e) {
            ui.showErr(e.getMessage());
            tasks = new TaskList();
        } 
    }

    /**
     * Returns the string representation of the the tasks list 
     * managed by this duke instance.
     *  
     * @return String representation of the tasks list.
     */
    public String getTaskListString() {
        return String.format("%s", tasks.toString());
    }

    /**
     * Runs the main logic of Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                return Main.EXIT_SIGNAL;
            } else {
                return c.execute(tasks, ui, storage);
            }
        } catch (InvalidCommandException e) {
            return ui.getErrMsg(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            if (tasks.getNumOfTasks() > 0) {
                return ui.getErrMsg(String.format("Thats an invalid number!\n" 
                        + "Please enter a number between %d and %d", 1, tasks.getNumOfTasks()));
            } else {
                return ui.getErrMsg("You can't do that! There is no task in the list!");
            }
        }
    }
}