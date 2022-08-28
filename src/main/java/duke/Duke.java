package duke;

import duke.command.Command;

/**
 * Main class of Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of Duke.
     * @param filePath File path of the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
    }

    public Message getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return new Message(c.execute(tasks, ui, storage), c.isExit(), false);
        } catch (DukeException e) {
            return new Message(ui.getError(e.toString()), false, true);
        }
    }

    public Message getGreeting() {
        return new Message(ui.getGreeting(), false, false);
    }

}
