package duke;

import java.io.FileNotFoundException;

/**
 * Main class for Duke application.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param path filepath of Duke.txt file.
     */
    public Duke(String path) {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(path);
        try {
            storage.readFromFile(taskList);
        } catch (FileNotFoundException e) {
            ui.printfileNotFound();
            taskList = new TaskList();
        }
    }

    /**
     * Returns the response corresponding to user input.
     * @param command user input.
     * @return response as a String.
     */
    public String getResponse(String command) {
        return Parser.parse(command, taskList, ui, storage);
    }
}


