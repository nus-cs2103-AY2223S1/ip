package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.managers.ParserManager;
import duke.managers.StorageManager;
import duke.managers.TaskManager;
import duke.managers.UiManager;

/**
 * Initializes the Duke application and initiates a conversation with the user.
 *
 * @author Emily Ong Hui Qi
 */
public class Duke {
    /**
     * Name of the chatbot
     */
    private static final String NAME = "Duke";

    /**
     * The greeting message used by the chatbot when the application starts
     */
    private static final String MESSAGE_GREETING = String.format("Hello! I'm %s\nWhat can I do for you?", Duke.NAME);

    /**
     * The main event loop of the application.
     *
     * @param args Unused arguments
     */
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
        ui.print(Duke.MESSAGE_GREETING);

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
