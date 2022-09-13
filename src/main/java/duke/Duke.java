package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;

/**
 * Represents the chatBot Duke.
 */
public class Duke {
    private TaskList listOfTasks;
    private FileStorage storage;
    private Ui ui;

    private Parser parser;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        storage = new FileStorage();
        parser = new Parser();
        ui = new Ui();
        try {
            initializeDataFile();
            listOfTasks = new TaskList(storage.retrieveFileContents());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            listOfTasks = new TaskList();
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

    /**
     * Returns Duke's response for the user's input to the GUI.
     *
     * @param input The given user input.
     * @return The string containing Duke's response.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = parser.parse(input.toLowerCase());
            assert command != null : "Command should not be null";
            response = command.execute(listOfTasks, storage, ui);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        assert !response.equals("") : "Response should not be empty";
        return response;
    }
}
