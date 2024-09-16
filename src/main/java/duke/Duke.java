package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.exceptions.DukeException;


/**
 * Chatbot that read user input to modify a tasklist.
 */
public class Duke {

    private final Storage STORAGE;

    /**
     * Construct a <code>Duke</code> instance that acts on file.
     *
     * @param filePath path of file.
     */
    public Duke(String filePath) {
        STORAGE = new Storage(filePath);
        try {
            STORAGE.load();
            Ui.printSuccessfulLoad();
        } catch (IOException e) {
            Ui.printFailedLoad();
        }
    }

    /**
     * Create a string in response to a command for GUI use.
     *
     * @param line Command from gui.
     * @return Response to the Command.
     */
    public String getResponse(String line) {
        try {
            Command command = Parser.parse(line);
            return command.execute(this.STORAGE);
        } catch (DukeException de) {
            return Ui.printError(de.getMessage());
        }
    }



}

