package duke;

import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.gui.DialogBox;
import duke.parser.DukeParser;
import duke.storage.Storage;
import duke.tasklist.TaskList;



/**
 * Main Duke class.
 */
public class Duke {

    private static final String FILE_PATH = "src/main/java/duke/data.txt";

    // String array used to store duke.tasks
    private static TaskList taskList;
    private Storage storage;
    private DukeParser parser;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage(FILE_PATH);
        try {
            this.taskList = this.storage.load();
        } catch (Exception e) {
            this.taskList = new TaskList();
        }
        this.parser = new DukeParser(this.taskList);
    }

    /**
     * Returns response from Duke when given an input
     * @param input User input to Duke
     * @return Duke's response to user input
     */
    public String getResponse(String input) {
        try {
            parser.parseInput(input);
            Command command = parser.execute();
            return command.execute(this.taskList, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}


