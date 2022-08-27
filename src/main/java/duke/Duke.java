package duke;

import java.io.FileNotFoundException;

import duke.command.Command;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.home") + "/data/duke.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets a response from Duke based on the user input.
     *
     * @param fullCommand User input.
     * @return Response from Duke.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
