package sky;

import java.io.IOException;

import sky.command.Command;
import sky.exception.TextNoMeaningException;

/**
 * The Sky class encapsulates a sky chat bot that keep tracks of tasks.
 */
public class Sky {
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a Sky object with file path being "data/sky.txt".
     */
    public Sky() {
        this.storage = new Storage("data/sky.txt");
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Returns the response of the Sky chat bot after it parses user input.
     *
     * @param input The user input in the form of a String.
     * @return The response of the Sky chat bot in the form of a String.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage);
        } catch (TextNoMeaningException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
