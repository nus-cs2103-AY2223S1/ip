package Duke;

import Duke.commands.Command;
import java.util.ArrayList;

import java.util.List;
import Duke.gui.Main;
import javafx.application.Application;
import java.io.IOException;

/* Inspiration taken from Bag Devesh Kumar and Zizheng ip to solve some prevalent issues
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.fileToList());
    }

    public String getResponse(String input) throws DukeException, IOException {
        Command c = Parser.parse(input);
        String message = c.execute(tasks, ui, storage);
        return message;
    }
}











