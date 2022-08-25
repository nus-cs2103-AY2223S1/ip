package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parse.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * The main class of chatbot Duke.
 */

public class Duke {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the Duke object.
     * @param filePath The string for the file that has the previously saved tasks.
     */
    public Duke(String filePath) {
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
        ui = new Ui();

        try {
            ui.displayLoading();
            storage.ReadFileContent(new File(filePath), taskList);
            ui.displayLoadingSuccess();
        } catch (FileNotFoundException e) {
            ui.displayLoadingError();
        }
    }

    /**
     * Runs the chatbot until the user decide to exit.
     */
    public void run() {
        ui.displayHello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.GetUserInput();
                Command c = parser.parse(input);
                c.execute(taskList,ui,storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

}
