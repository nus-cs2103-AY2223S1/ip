import handlers.CommandType;
import handlers.DukeCommand;
import exceptions.DukeException;
import models.CommandManager;
import models.TaskManager;
import utils.DukeErrorPrinter;
import utils.DukePrinter;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        CommandManager commandManager = new CommandManager();

        // Greet the user
        DukePrinter.print(Duke.GREETING_MESSAGE);

        while (scanner.hasNextLine()) {
            // Receive the command entered by the user
            String inputLine = scanner.nextLine();
            String[] input = inputLine.split(" ");
            String command = input[0];
            String arguments = inputLine.replaceFirst(command, "").strip();

            CommandType commandType;
            try {
                commandType = CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                DukeErrorPrinter.print(Duke.UNKNOWN_COMMAND_ERROR);
                continue;
            }

            DukeCommand dukeCommand = commandManager.get(commandType);
            try {
                String status = dukeCommand.execute(taskManager, arguments);
                DukePrinter.print(status);
            } catch (DukeException e) {
                DukeErrorPrinter.print(e.getMessage());
            }

            if (commandManager.isTerminatingCommand(commandType)) {
                break;
            }
        }

        scanner.close();
    }
}
