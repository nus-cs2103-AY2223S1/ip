package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Represents a <code>Duke</code> programme.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Creates a Duke program.
     *
     * @param filePath directory to store task data
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }


    /**
     * Returns Duke's response based on user's input.
     *
     * @param userInput input from user
     * @return duke's response
     */
    public String getResponse(String userInput) {
        String dukeResponse;
        try {
            Command c = parser.parse(userInput);
            dukeResponse = c.execute(tasks);
            storage.saveData(tasks);
            return dukeResponse;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
