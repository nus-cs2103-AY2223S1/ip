package duke.dukerobot;

import java.io.IOException;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasklist.TaskList;


/**
 * Represents the duke robot. Contains the main function.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Class constructor.
     */
    public Duke(String filepath)  {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.loadTasks();
        } catch (Exception e) {
            this.taskList = null;
            this.storage = null;
        }

    }



    /**
     * Instruct the robot to show user interface and read in command and execute.
     * Catch and dispose exceptions.
     */
    public String getResponse(String input) throws DukeException {
        //this.ui.showWelcomeMessage()
        return Parser.parse(input, this.taskList, this.storage);

    }

        //ui.showGoodbyeMessage();


    /**
     * The start point of the program. Run the duke itself.
     * @param args
     */
    //public static void main(String[] args) {
      // new Duke("data/duke.txt").run();
    //}
}
