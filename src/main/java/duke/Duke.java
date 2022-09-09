package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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

    public void loadTask() throws DukeException {
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw e;
        }
    }

    public String getResponse(String input) {
        String res;
        try {
            Command c = Parser.parse(input);
            res = c.execute(tasks, storage);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid parameters (require Integer) for the command!");
        } catch (DukeException e) {
            res = Ui.getErrorMessage(e.getMessage());
        }
        assert res != null;
        return res;
    }
}
