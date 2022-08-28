package duke.services;

import duke.utils.Commands;

import static duke.utils.Commands.UNKNOWN;

public class Parser {

    public String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

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