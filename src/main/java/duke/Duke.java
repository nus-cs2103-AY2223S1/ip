package duke;

import duke.command.Command;

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

    /**
     * Creates a Duke object with its relevant filepath.
     *
     */
    public Duke() {
        ui = new Ui();
        String filePath = "data/tasks.txt";
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            output = e.toString();
        }
        return output;
    }


}

