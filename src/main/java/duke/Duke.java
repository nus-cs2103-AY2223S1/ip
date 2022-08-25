package duke;

import duke.command.Command;
import duke.exception.IncompleteInputException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidInputException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        new Duke(home + "/data/duke.txt").run();
    }
}
