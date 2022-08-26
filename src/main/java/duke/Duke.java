package duke;

import duke.command.Command;
import duke.internal.DukeException;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The main class of the Duke application.
 */
public class Duke {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke(Path path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (IOException e) {
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }
    
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, storage, ui);
            return ui.flush();
        } catch (DukeException e) {
            return e.getMessage() + ".";
        } catch (Exception e) {
            return e.toString();
        }
    }
    
    public String getWelcome() {
        return ui.showWelcome().flush();
    }
}
