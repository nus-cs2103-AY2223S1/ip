package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;

/**
 * Duke is the main class of the program to save and keep track of ur task in a text file.
 *
 * @author Lian Guo Yang
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor of Duke to initialise ui for showing messages and storage to store tasks
     *
     * @param filePath path of file
     * @throws FileNotFoundException if file is not found
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.toString());
            taskList = new TaskList();
        }
    }

    /**
     * Run the program and interact with the user
     *
     * @throws DukeException If invalid commands and arguments
     * @throws IOException If invalid inputs
     */
    public void run() throws DukeException, IOException {
        ui.showGreetMsg();
        while (true) {
            ui.showLine();
            String input = ui.readCommand();
            Command currCmd = ui.run(input);
            currCmd.execute(ui, taskList);

            if (currCmd instanceof ExitCommand) {
                break;
            }
        }
        storage.writeFile(taskList);
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/duke.txt").run();
    }
}
