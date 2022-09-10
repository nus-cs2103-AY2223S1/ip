package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class represents the chatbot
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new Duke chatbot
     */
    public Duke() {
        Storage storage = new Storage("./data/duke.txt");
        ui = new Ui();
        this.storage = storage;
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(ui, storage, tasks);
            return response;
        } catch (DukeException e) {
            return ui.print(e.getMessage());
        }
    }
}
