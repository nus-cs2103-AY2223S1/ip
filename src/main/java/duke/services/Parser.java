package duke.services;

import duke.utils.Commands;
import static duke.utils.Commands.UNKNOWN;

public class Parser {
    /**
     * Parses a string input and returns an array containing the command and description.
     * @param input User input containing command and description.
     * @return Array containing command and description.
     */
    public String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses a string input and returns the command to be executed.
     * @param input User input containing command.
     * @return Command to be executed.
     */
    public Commands parseCommand(String input) {
        String[] wholeCommand = input.split(" ", 2);
        Commands command;
        try {
            command = Commands.valueOf(wholeCommand[0].toUpperCase());
            System.out.println(command.toString());
        } catch (IllegalArgumentException exception) {
            command = UNKNOWN;
        }
        return command;
    }
}
