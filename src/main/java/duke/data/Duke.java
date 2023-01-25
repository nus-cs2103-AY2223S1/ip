package duke.data;

import java.util.Scanner;

import duke.commands.Command;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Main launcher class for the Duke chatbot.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for a Duke bot.
     */
    public Duke() {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage();
        this.taskList = this.storage.load();
    }

    /**
     * Processes user input.
     */
    public String getResponse(String input) {

        try {
            Command c = Parser.parseCommand(input);
            ui.setUserInput(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException de) {
            return de.getMessage();
        }
    }

    /**
     * Checks if user command is a terminating command.
     *
     * @param input user input to process.
     * @return boolean indicating whether Duke terminates.
     */
    public boolean isEnd(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.isEnd();
        } catch (DukeException de) {
            return false;
        }
    }


}
