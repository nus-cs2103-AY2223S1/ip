package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public static final String FILE_PATH = "data/duke.txt";

    /**
     * The main game.
     *
     * @param filePath Directory of the filepath.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Craft's Duke's response to the given user input.
     *
     * @param fullCommand User input command.
     * @return Duke's response to the user's input command.
     */
    public String getResponse(String fullCommand) {

        ByteArrayOutputStream outputString = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputString));

        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                try {
                    storage.save(tasks.getList());
                } catch (IOException e) {
                    throw e;
                }
            }
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
        return outputString.toString();
    }
}
