import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private final Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage("data", "data/tasks");

        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            duke.ui.showErrorMessage(e);
            // Load with empty list instead.
            tasks = new TaskList();
        }

        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();
                Command command = duke.parser.parseCommand(userInput);
                // Populate command with tasks.
                command.setData(tasks);
                CommandResult result = command.execute();
                if (result.shouldExit()) {
                    // Exit application by exiting the scan loop.
                    break;
                }
                if (result.shouldUpdateFile()) {
                    storage.save(tasks);
                }
                duke.ui.showResult(result);
            } catch (DukeException | IOException e) {
                duke.ui.showErrorMessage(e);
            } catch (NumberFormatException e) {
                // Handles case where user inputs an invalid number.
                duke.ui.showErrorMessage("Invalid number!");
            }
        }

        duke.ui.showGoodbyeMessage();

        // Close scanner.
        scanner.close();
    }
}
