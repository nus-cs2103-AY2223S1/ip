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

    private Parser parser;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        storage = new FileStorage(System.getProperty("user.home"));
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
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = parser.parse(input);
            response = command.execute(listOfTasks, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }
}
