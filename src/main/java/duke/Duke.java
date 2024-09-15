package duke;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exception.DukeException;


/**
 * The Duke class encapsulates a chatbot that helps users to keep track of their tasks.
 */
public class Duke {
    private static final String pathName = "text/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    protected Ui ui;

    public Duke() {
        this.storage = new Storage(pathName);
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    public void load() throws IOException {
        File file = new File(Duke.pathName);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        this.storage.loadTasks(this.taskList);
    }

    public String getResponse(String input) throws IOException {
        try {
            Command c = Parser.parseInput(input);
            return c.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException d) {
            return d.getMessage();
        } catch (DateTimeParseException e) {
            return "Do format your time in yyyy-MM-dd HH:mm";
        }
    }
}
