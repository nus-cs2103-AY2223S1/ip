package sky;

import java.io.IOException;

import sky.command.Command;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;

/**
 * The Sky class encapsulates a sky chat bot that keep tracks of tasks.
 */
public class Sky {
    private TaskList taskList;
    private Storage storage;
    private History history;

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
        this.history = new History(this, this.taskList);
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
            return c.execute(taskList, storage, history);
        } catch (TextNoMeaningException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    /**
     * Sets the sky chat bot's task list to the specified task list.
     *
     * @param newTaskList The new task list to be set as the chat bot task list.
     */
    public void changeHistory(TaskList newTaskList) {
        this.taskList = newTaskList;
    }
}
