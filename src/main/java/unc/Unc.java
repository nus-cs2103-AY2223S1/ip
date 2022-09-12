package unc;

import unc.command.Command;

/**
 * Main class where program is run.
 */
public class Unc {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor
     *
     * @param filePath Directory to save data.
     */
    public Unc(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                System.exit(0);
            }
            return c.execute(taskList, ui, storage);
        } catch (UncException e) {
        }
        return "Duke heard: " + input;
    }
}
