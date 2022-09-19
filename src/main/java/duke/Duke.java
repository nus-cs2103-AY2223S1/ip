package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.Parser;
import duke.data.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/** Contains storage, tasks and ui to help interact with user and execute commands */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the Storage object with the saved tasks.
     *
     * @throws IOException
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays response in GUI.
     *
     * @param input is the command input.
     * @return the response for the command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }

}
