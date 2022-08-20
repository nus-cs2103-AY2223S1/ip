package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public Duke() {
        this.parser = new Parser();
        this.storage = new Storage("data", "data/tasks");
        this.ui = new Ui();

        TaskList tasks;
        try {
            // Attempt to load tasks from storage.
            tasks = this.storage.load();
        } catch (DukeException e) {
            this.ui.showErrorMessage(e);
            // Load empty list if fail to load from storage.
            tasks = new TaskList();
        }
        this.tasks = tasks;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.ui.showWelcomeMessage();

        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                Command command = this.parser.parseCommand(userInput);
                // Populate command with tasks.
                command.setData(this.tasks);
                CommandResult result = command.execute();
                if (result.shouldExit()) {
                    // Exit application by exiting the scan loop.
                    break;
                }
                if (result.shouldUpdateFile()) {
                    // Save to storage.
                    this.storage.save(this.tasks);
                }
                this.ui.showResult(result);
            } catch (DukeException | IOException e) {
                this.ui.showErrorMessage(e);
            } catch (NumberFormatException e) {
                // Handles case where user inputs an invalid number.
                this.ui.showErrorMessage("Invalid number!");
            }
        }

        this.ui.showGoodbyeMessage();
        // Close scanner.
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
