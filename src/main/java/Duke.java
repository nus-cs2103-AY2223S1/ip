import commands.ByeCommand;
import commands.Command;
import exceptions.DukeException;
import managers.StorageManager;
import managers.TaskManager;
import managers.ParserManager;
import managers.UiManager;

/**
 * @author Emily Ong Hui Qi
 */
public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";

    // The greeting message used by the chatbot when the program starts
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?", Duke.NAME);

    public static void main(String[] args) {
        UiManager ui = new UiManager();
        StorageManager storageManager;

        try {
            storageManager = new StorageManager();
        } catch (DukeException e) {
            ui.print(e.getMessage());
            return;
        }

        TaskManager taskManager = new TaskManager(storageManager.getTaskStorage());
        ParserManager parserManager = new ParserManager();

        // Greet the user
        ui.print(Duke.GREETING_MESSAGE);

        boolean isExit = false;

        while (!isExit) {
            try {
                // Receive the command entered by the user
                String fullCommand = ui.readCommand();
                Command command = parserManager.parseCommand(fullCommand);
                command.execute(taskManager, ui);
                isExit = ByeCommand.is(command);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }

        ui.close();
    }
}
