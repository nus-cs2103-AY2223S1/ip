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
    private FileStorage taskStorage;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a Duke object.
     * @param home The string for the OS of the user.
     */
    private Duke(String home) {
        ui = new Ui();
        taskStorage = new FileStorage(home);
        parser = new Parser();
        try {
            initializeDataFile();
            listOfTasks = new TaskList(taskStorage.retrieveFileContents());
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
                command.execute(listOfTasks, taskStorage, ui);
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * Creates the file and directory to store the taskList data.
     */
    private void initializeDataFile() {
        if (!taskStorage.isDirectoryPresent()) {
            taskStorage.createDirectory();
        }
        if (!taskStorage.isFilePresent()) {
            taskStorage.createFile();
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.home")).run();
    }
}
