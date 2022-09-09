package duke;

import java.io.IOException;
import java.nio.file.Path;

import duke.command.Command;
import duke.internal.DukeException;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.TaskList;

/**
 * The main class of the Duke application.
 */
public class Duke {
    private final Parser parser;
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Returns a new Duke object.
     *
     * @param path the path to the storage file of tasks
     */
    public Duke(Path path) {
        this.parser = new Parser();
        this.storage = new Storage(path);
        this.ui = new Ui();
        TaskList tasks;
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parseString(input);
            command.execute(tasks, storage, ui, parser);
            return ui.flush();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String getWelcome() {
        return "Hello! I'm the Knight!\n"
                + "You can also call me Ghost if you like!\n"
                + "What can I do for you?";
    }
}
