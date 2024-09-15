package duke.services;

import static duke.utils.Commands.UNKNOWN;

import duke.utils.Commands;

/**
 * A class that handles parsing of user inputs.
 */
public class Parser {
    /**
     * Parses and returns an array of Duke Command components from user input.
     *
     * @param input User input to parse.
     * @return Components of the Duke Command from given user input.
     **/
    public String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses and returns the type of Duke Command from an input.
     *
     * @param input User input to parse for Duke Command.
     * @return Respective Commands ENUM.
     **/
    public Commands parseCommand(String input) {
        String[] fullCommand = input.split(" ", 2);
        Commands command;
        try {
            command = Commands.valueOf(fullCommand[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            command = UNKNOWN;
        }
        return command;
    }
}
