import commandhandler.Command;
import commandhandler.CommandFactory;
import commandhandler.CommandHandler;

import data.TaskList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    // ChatBot constants
    public static final String NAME = "Duke";
    // UI constants
    public static final String INDENT_CHAR = "\t";
    public static final String LINE_STR = "-".repeat(50);

    private static final TaskList taskList = new TaskList();

    /**
     * Utility function to print line to STDOUT
     *
     * @param line   Line to printed to STDOUT
     * @param indent Number of indentation prefix
     */
    private static void printLine(String line, int indent) {
        System.out.printf("%s%s\n", INDENT_CHAR.repeat(indent), line);
    }

    /**
     * Utility function to format Duke's response and display it to STDOUT
     * <p>
     * Note: Defaults to 1 indent prefix for every response
     * </p>
     *
     * @param response A single response
     */
    private static void respond(String response) {
        printLine(LINE_STR, 1);
        printLine(response, 1);
        printLine(LINE_STR, 1);
    }

    /**
     * Utility function to format Duke's responses and display it to STDOUT
     * <p>
     * Note: Defaults to 1 indent prefix for every response
     * </p>
     *
     * @param responses A collection of lines of responses
     */
    private static void respond(List<String> responses) {
        printLine(LINE_STR, 1);
        for (String respLine : responses) {
            printLine(respLine, 1);
        }
        printLine(LINE_STR, 1);
    }

    public static void main(String[] args) {
        // Greetings
        respond(Arrays.asList(String.format("Hi I'm %s", NAME), "What can I do for you?"));

        // Chat
        CommandFactory commandFactory = new CommandFactory();
        boolean terminate = false;
        Scanner input = new Scanner(System.in);
        while (!terminate && input.hasNextLine()) {
            String query = input.nextLine();
            List<String> tokens = Arrays.asList(query.split(" "));

            String commandToken = tokens.get(0);
            Command command = commandFactory.parseCommand(commandToken);

            // Handle special commands
            if (command == Command.BYE) {
                terminate = true;
                respond("Bye. Hope to see you again soon!");
                continue;
            }
            if (command == Command.UNKNOWN) {
                respond("Unknown command!");
                continue;
            }

            CommandHandler commandHandler = commandFactory.getCommandHandler(command, taskList);
            // Validate command
            if (!commandHandler.validateCommand(tokens)) {
                respond(String.format("[%s] Invalid command parameters!",
                    command.toString().toLowerCase()));
            } else {
                List<String> commandResponse = commandHandler.run(tokens);
                respond(commandResponse);
            }
        }
    }
}
