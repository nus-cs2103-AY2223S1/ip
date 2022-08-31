package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The Duke class that instantiates instances of duke.
 *
 * Duke is a ChatBot that performs different actions
 * based on commands provided by user.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Duke {
    // Initialise variables
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for ChatBot, Duke.
     *
     * @param filePath Location of save file.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.printErr(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Returns a list of String of max size 2 to MainWindow.
     * String[0] stores response to the user.
     * String[1] stores the state of the program, where
     * "0" = Program should not quit after this.
     * "1" = Program should quit after this.
     * @param input user input
     * @return a list of String of size 2
     */
    public String[] getResponse(String input) {
        // Split input to its proper form
        String command;
        String description;
        String[] response = new String[2];
        if (input.contains(" ")) {
            int index = input.indexOf(' ');
            command = input.substring(0, index);
            description = input.substring(index);
        } else {
            command = input;
            description = "";
        }

        try {
            Command c = Parser.parse(command, description);

            response[0] = c.execute(tasks, ui, storage);
            response[1] = c.isExit() ? "1" : "0";
            return response;
        } catch (DukeException e) {
            response[0] = ui.printErr(e.getMessage());
            response[1] = "0";
            return response;
        }
    }
}
