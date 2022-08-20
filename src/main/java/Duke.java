import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private final Ui ui;

    public Duke() {
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);

        Storage storage = new Storage("data", "data/tasks");

        ArrayList<Task> tasks;
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            duke.ui.showErrorMessage(e);
            // Load with empty list instead.
            tasks = new ArrayList<>();
        }

        scanLoop:
        while (scanner.hasNext()) {
            try {
                String userInput = scanner.nextLine();

                Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s?(?<arguments>.*)");
                Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);
                String commandWord, arguments;
                if (matcher.matches()) {
                    commandWord = matcher.group("commandWord");
                    arguments = matcher.group("arguments");
                } else {
                    throw DukeException.unknownCommand;
                }
                Command command;

                // Handle the various commands.
                switch (commandWord) {
                    case "bye":
                        // Stops the application, by breaking out of the scan loop.
                        break scanLoop;
                    case "list":
                        command = new ListCommand(tasks);
                        break;
                    case "mark": {
                        command = new MarkCommand(tasks, arguments);
                        break;
                    }
                    case "unmark": {
                        command = new UnmarkCommand(tasks, arguments);
                        break;
                    }
                    case "delete": {
                        command = new DeleteCommand(tasks, arguments);
                        break;
                    }
                    case "todo": {
                        command = new TodoCommand(tasks, arguments);
                        break;
                    }
                    case "deadline": {
                        command = new DeadlineCommand(tasks, arguments);
                        break;
                    }
                    case "event": {
                        command = new EventCommand(tasks, arguments);
                        break;
                    }
                    default:
                        throw DukeException.unknownCommand;
                }
                CommandResult result = command.execute();
                duke.ui.showResult(result);
                if (result.shouldUpdateFile()) {
                    storage.save(tasks);
                }
                duke.ui.showLineBreak();
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
