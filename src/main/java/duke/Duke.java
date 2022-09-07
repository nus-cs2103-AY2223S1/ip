package duke;

import command.Command;
import exception.DukeException;
import gui.DukeGui;
import javafx.application.Application;
import javafx.application.Platform;
import tasklist.TaskList;
import util.Parser;
import util.Storage;

/**
 * Entry point of the application that initialises and run the app.
 *
 * @author Bryan Lim Jing Xiang
 */
public class Duke {
    private TaskList dukelist = new TaskList();
    private Storage storage;

    /**
     * @param filePath File path of the internal save file
     */
    public Duke(String filePath) {
        storage = Storage.initialize(filePath);
        storage.load(dukelist);
    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parseInputLine(input);
            cmd.execute(dukelist, storage);
            if (cmd.getIsTerminated()) {
                Platform.exit();
            }
            return cmd.getOutputMessage();
        } catch (DukeException e) {
            return e.errorMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
