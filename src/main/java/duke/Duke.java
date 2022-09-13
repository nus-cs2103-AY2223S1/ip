package duke;

import duke.command.Command;
import javafx.application.Application;

/**
 * Represents main class for the Duke programme
 *
 * @author benjytan45678
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Container container;

    private ContactMap contacts;

    /**
     * Creates a Duke object with its relevant filepath.
     *
     */
    public Duke() {
        ui = new Ui();
        String taskFilePath = "data/tasks.txt";
        try {
            storage = new Storage(taskFilePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        String contactFilePath = "data/contacts.txt";
        try {
            container = new Container(contactFilePath);
            contacts = new ContactMap(container.load());
        } catch (DukeException e) {
            contacts = new ContactMap();
        }
    }
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage,container,contacts);
        } catch (DukeException e) {
            output = e.toString();
        }
        return output;
    }



}

