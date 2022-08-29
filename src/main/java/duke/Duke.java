package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;


/**
 * This is the Main Duke program for our chatbot (our bot prefers to be addressed as Bob).
 * Bob is a personal assistant chatbot that helps you keep track of what you have to do.
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
     * @param filePath File path where our data is stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts and launches Duke (Bob).
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printHorizontalLine();
                Command c = RequestHandler.handleRequest(fullCommand);
                c.run(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IncompleteInputException e) {
                ui.printAnyOtherMessage(e.getMessage());
            } catch (InvalidInputException e) {
                ui.printAnyOtherMessage(e.getMessage());
            } catch (InvalidCommandException e) {
                ui.printAnyOtherMessage(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printAnyOtherMessage("Please key in a valid date (yyyy-mm-dd)");
            } finally {
                ui.printHorizontalLine();
            }
        }
    }

    /**
     * The main method.
     *
     * @param args main arguments
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        new Duke(home + "/data/duke.txt").run();
    }
}
