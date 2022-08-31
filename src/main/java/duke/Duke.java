package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;




/**
 * This is the Main Duke (Bob) program for our chatbot (our bot prefers to be addressed as Bob).
 * Bob is a personal assistant chat bot that helps you keep track of what you have to do.
 *
 * @author Eugene Tan
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor for our chatbot.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Handles Bob response to user.
     *
     * @param userInputCommand input user types in
     * @return response of Bob
     */

    public String getResponse(String userInputCommand) {
        boolean isExit = false;
        try {
            Command command = RequestHandler.handleRequest(userInputCommand);
            String bobResponse = command.run(tasks, ui, storage);
            isExit = command.isExit();
            if (isExit) {
                System.exit(0);
            }
            return bobResponse;
        } catch (IncompleteInputException e) {
            return e.getMessage();
        } catch (InvalidInputException e) {
            return e.getMessage();
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }
    }

}
