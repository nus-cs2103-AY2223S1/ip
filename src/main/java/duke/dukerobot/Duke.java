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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    /**
     * Class constructor.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.loadTasks();
        } catch (IOException e) {
            this.ui.showFileCreatingError();
        }
    }



    /**
     * Instruct the robot to show user interface and read in command and execute.
     * Catch and dispose exceptions.
     */
    public String getResponse(String input) {
        //this.ui.showWelcomeMessage()
        try {
            Command c = Parser.parse(input);
            return c.execute(this.taskList, this.storage);
        } catch (DukeException e) {
            return ui.showDukeException(e.getMessage());
        }
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
