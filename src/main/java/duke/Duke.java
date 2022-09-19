package duke;

import commands.Command;
import dukeexceptions.DukeException;
import tasks.TaskList;

import javafx.util.Pair;


public class Duke {
    TaskList taskList;
    String filePath;
    private Storage s;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path of file to store data in or retrieve data from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.s = new Storage(this.filePath);
        this.taskList = s.open();
    }

    /**
     * Generates a response to user input.
     *
     * @param input User input received to be executed by program.
     * @return String to be displayed by program.
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






