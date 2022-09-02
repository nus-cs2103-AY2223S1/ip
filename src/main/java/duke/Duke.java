package duke;

import commands.ByeCommand;
import commands.Command;
import dukeexceptions.DukeException;
import tasks.TaskList;


public class Duke {
    TaskList taskList;
    String filePath;
    private Storage s;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.s = new Storage(this.filePath);
        this.taskList = s.open();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command comm = Parser.parse(input);
            return comm.execute(this.taskList, this.ui, this.s);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}






