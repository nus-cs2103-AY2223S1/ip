import command.Command;
import command.CommandException;
import command.CommandFactory;
import command.CommandHandler;

import data.TaskList;

import java.util.Scanner;

public class Duke {

    // ChatBot constants
    public static final String NAME = "Duke";
    // UI constants
    public static final String LINE_STR = "-".repeat(50);

    private static final TaskList taskList = new TaskList();

    private static void respond(String response) {
        System.out.printf("\t%s\n", LINE_STR);
        System.out.printf("\t%s\n", response.replaceAll("\\n", "\n\t"));
        System.out.printf("\t%s\n", LINE_STR);
    }

    private static void respondError(String errorMsg) {
        respond(String.format("X %s", errorMsg));
    }

    public static void main(String[] args) {
        // Greetings
        respond(String.format("Hi I'm %s\n%s", NAME, "What can I do for you?"));

        // Chat
        CommandFactory commandFactory = new CommandFactory();
        boolean terminate = false;
        Scanner input = new Scanner(System.in);
        while (!terminate && input.hasNextLine()) {
            String commandStr = input.nextLine();
            try {
                Command command = commandFactory.parseCommand(commandStr);
                if (command == Command.BYE) {
                    terminate = true;
                    respond("Bye. Hope to see you again soon!");
                    continue;
                }
                CommandHandler commandHandler = commandFactory.getCommandHandler(command,
                    commandStr);
                respond(commandHandler.run(taskList));
            } catch (CommandException commandError) {
                respondError(commandError.getMessage());
            }
        }
    }
}
