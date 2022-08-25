package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main entry point of the Duke application.
 * Initializes the application and is responsible for user interaction.
 */
public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    /**
     * Constructor for a Duke application instance.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage("data", "data/tasks");
        ui = new Ui();

        TaskList tasks;
        try {
            // Attempt to load tasks from storage.
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showErrorMessage(e);
            // Load empty list if fail to load from storage.
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    /**
     * Runs the Duke application and handles user interaction.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcomeMessage();

        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                Command command = parser.parseCommand(userInput);
                // Populate command with tasks.
                command.setData(tasks);
                CommandResult result = command.execute();
                if (result.shouldExit()) {
                    // Exit application by exiting the scan loop.
                    break;
                }
                if (result.shouldUpdateFile()) {
                    // Save to storage.
                    storage.save(tasks);
                }
                ui.showResult(result);
            } catch (DukeException | IOException e) {
                ui.showErrorMessage(e);
            } catch (NumberFormatException e) {
                // Handles case where user inputs an invalid number.
                ui.showErrorMessage("Invalid number!");
            }
        }

        ui.showGoodbyeMessage();
        // Close scanner.
        scanner.close();
    }

    /**
     * Entry point of the application.
     *
     * @param args Args is ignored.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
