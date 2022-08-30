package duke;

import duke.*;
import duke.commands.Command;
import javafx.scene.image.Image;

/**
 * duke.Duke Program for tracking Tasks.
 */
public class Duke {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * duke.Duke constructor.
     *
     * @param filePath filePath of file to store Task information.
     * @param tempFilePath filePath of temporary file to store information for rewriting.
     */
    public Duke(String filePath, String tempFilePath) {
        ui = new Ui();
        storage = new Storage(filePath, tempFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Parse user input to String.
     *
     * @param input user input.
     * @return String of command output or error.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            return output;
        } catch (DukeException e) {
            return ui.printError(e);
        }
    }
}