package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.StoredTasks;
import duke.util.Ui;

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
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.storedTasks = new StoredTasks(FILE_DIR, FILE_PATH);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(this.storedTasks.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void runDuke() {
        while (true) {
            String command = this.ui.getUserCommand();
            try {
                Parser.handleCommand(command, this.tasks);
                if (command.equals("bye")) {
                    this.storedTasks.save(this.tasks);
                    break;
                }
            } catch (DukeException de) {
                this.storedTasks.save(this.tasks);
                System.out.println(de);
                break;
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}