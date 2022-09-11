package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The main class of chatbot Duke.
 */

public class Duke  {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for the Duke object.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();

        try {
            storage.readFileContent(new File("duke.txt"), taskList);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a response based on the user's input.
     *
     * @param input The user input.
     * @return A string containing the response.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            assert command == null : "Command should not be null!";
            return command.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
