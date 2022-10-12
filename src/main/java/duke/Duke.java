package duke;

import duke.commands.Command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.IOException;
import javafx.application.Platform;

/**
 * A program that manages and stores tasks inputted by a user
 */
public class Duke {

    private static final String FILE_NAME = "dukeList.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for the Duke class
     *
     * @param filePath Path of the file that stores the list of tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(Paths.get(filePath));
        try {
            tasks = new TaskList(storage.loadTasks(), storage);
            ui.printLoadingSuccessMessage();
        } catch (IOException err) {
            ui.printLoadingError(filePath);
            tasks = new TaskList(new ArrayList<>(), storage);
        } catch (DateTimeParseException err) {
            ui.printCorruptedError();
            tasks = new TaskList(new ArrayList<>(), storage);
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.printWelcomeMessage();
        String response;
        while (!this.tasks.getTaskListStatus()) {
            try {
                String inputCommand = ui.readInput();
                Command command = Parser.parse(inputCommand, tasks);
                response = command.execute(storage, tasks);
            } catch (DukeException err) {
                ui.printError(err.getMessage());
            }
        }
        ui.printExitMessage();
    }

    public String getWelcomeMessage() {
        return "Hello, my name is Duke!\nHow can I help you today?";
    }

    public String getResponse(String input) {
        String response = null;
        try {
            Command command = Parser.parse(input, tasks);
            response = command.execute(storage, tasks);
        } catch (DukeException dukeErr) {
            response = dukeErr.getMessage();
        } catch (DateTimeParseException dtErr) {
            response = "I don't recognise this time format."
                    + "\nThe format of the DateTime should be as follows:"
                    + "\nFor Deadlines --> dd/MM/yyyy[ HHmm]"
                    + "\nFor Events --> dd/MM/yyyy HHmm";
        } catch (AssertionError assertErr) {
            response = assertErr.getMessage();
        }
        if (tasks.getTaskListStatus()) {
            Platform.exit();
        }
        return response;
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
