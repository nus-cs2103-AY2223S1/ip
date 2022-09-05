package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.StoredTasks;
import duke.util.Ui;
import duke.util.command.Command;

import java.io.File;

/**
 * Duke class to run and execute the program.
 *
 * @author Kavan
 */
public class Duke {
    private static final String FILE_DIR = "data";
    private static final String FILE_PATH = FILE_DIR + File.separator + "duke.txt";

    private StoredTasks storedTasks;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.storedTasks = new StoredTasks(FILE_DIR, FILE_PATH);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(this.storedTasks.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.handleCommand(taskList, storedTasks);
        } catch (DukeException de) {
            this.storedTasks.save(this.taskList);
            System.out.println(de);
            return de.toString();
        }
    }
}