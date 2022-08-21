import exceptions.DukeException;
import enums.CommandType;
import commands.DukeCommand;
import managers.CommandManager;
import managers.StorageManager;
import managers.TaskManager;
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
        CommandManager commandManager = new CommandManager();

        // Greet the user
        ui.print(Duke.GREETING_MESSAGE);

        boolean isExit = false;

        while (!isExit) {
            // Receive the command entered by the user
            String fullCommand = ui.readCommand();
            String[] input = fullCommand.split(" ");
            String command = input[0];
            String arguments = fullCommand.replaceFirst(command, "").strip();

            CommandType commandType;
            try {
                commandType = CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                ui.print(Duke.UNKNOWN_COMMAND_ERROR);
                continue;
            }

            DukeCommand dukeCommand = commandManager.get(commandType);
            try {
                String status = dukeCommand.execute(taskManager, arguments);
                ui.print(status);
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }

            isExit = commandManager.isTerminatingCommand(commandType);
        }

        ui.close();
    }
}
