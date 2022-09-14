package duke;

import java.io.IOException;

//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.DukeUnknownCommandException;


/**
 * Chatbot that read user input to modify a tasklist
 */
public class Duke {

    private Storage storage;

    /**
     * Construct a <code>Duke</code> instance that acts on file.
     *
     * @param filePath path of file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.load();
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
            String res = command.execute(this.storage);
            System.out.println(res);
            return res;
        } catch (DukeException de) {
            return Ui.printError(de.getMessage());
        }
    }



}

