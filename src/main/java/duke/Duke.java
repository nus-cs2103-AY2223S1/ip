package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Represents the chatbot Duke.
 */
public class Duke {
    private TaskList listOfTasks;
    private FileStorage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Duke object.
     * @param home The string for the OS of the user.
     */
    private Duke(String home) {
        ui = new Ui();
        storage = new FileStorage(home);
        parser = new Parser();
        try {
            initializeDataFile();
            listOfTasks = new TaskList(storage.retrieveFileContents());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            listOfTasks = new TaskList();
        }
    }

    /**
     * Runs the chat session with the user until the user exits.
     */
    private void run() {
        ui.printIntro();
        while (ui.isScannerActive()) {
            String input = ui.retrieveUserInput();
            try {
                Command command = parser.parse(input);
                command.execute(listOfTasks, storage, ui);
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Creates the file and directory to store the taskList data.
     */
    private void initializeDataFile() {
        if (!storage.isDirectoryPresent()) {
            storage.createDirectory();
        }
        if (!storage.isFilePresent()) {
            storage.createFile();
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.home")).run();
    }
}
