import commands.ByeCommand;
import commands.Command;
import exceptions.DukeException;
import managers.StorageManager;
import managers.TaskManager;
import parser.Parser;
import ui.Ui;

/**
 * @author Emily Ong Hui Qi
 */
public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";

    // The greeting message used by the chatbot when the program starts
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?", Duke.NAME);

    private static final String UNKNOWN_COMMAND_ERROR = "I do not understand your command!";

    public static void main(String[] args) {
        Ui ui = new Ui();
        StorageManager storageManager;

        try {
            storageManager = new StorageManager();
        } catch (DukeException e) {
            ui.print(e.getMessage());
            return;
        }

        TaskManager taskManager = new TaskManager(storageManager.getTaskStorage());

        // Greet the user
        ui.print(Duke.GREETING_MESSAGE);

        boolean isExit = false;

        while (!isExit) {
            // Receive the command entered by the user
            String fullCommand = ui.readCommand();
            Command command = Parser.parseCommand(fullCommand);

            try {
                String status = command.execute(taskManager);
                ui.print(status);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }

            isExit = ByeCommand.is(command);
        }

        ui.close();
    }
}
