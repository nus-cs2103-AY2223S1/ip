package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Duke is an automated chat-bot which can help you manage your tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke.
     */
    public Duke() {
        storage = new Storage("data/tasks.txt");
        this.loadTask();
    }

    /**
     * Loads the task from storage.
     *
     * @throws DukeException Exceptions from loading the task.
     */
    public void loadTask() throws DukeException {
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw e;
        }
    }

    /**
     * Reads the user input and returns a string response from executing duke commands.
     *
     * @param input User input.
     * @return String response from executing duke commands.
     */
    public String getResponse(String input) {
        String res;
        try {
            Command c = Parser.parse(input);
            res = c.execute(tasks, storage);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid parameters (require Integer) for the command!");
        } catch (DukeException e) {
            res = e.getMessage();
        }

        assert res != null;
        return res;
    }
}
