import Command.Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Parser;
import Duke.TaskList;
import Duke.Storage;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private DukeUi ui;
    private final String FILE_PATH = "./data/tasks.txt";


    public Duke() {
        ui = new DukeUi();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
            DukeUi.sendMessage("load complete!");
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String getResponse(String input) {
        try {
            String[] userCommand = ui.readCommand(input);
            Command c = Parser.parse(userCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}